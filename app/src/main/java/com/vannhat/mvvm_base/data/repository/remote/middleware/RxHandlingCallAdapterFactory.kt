package com.vannhat.mvvm_base.data.repository.remote.middleware

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vannhat.mvvm_base.data.repository.remote.error.RetrofitException
import com.vannhat.mvvm_base.data.repository.remote.response.BaseErrorResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

class RxHandlingCallAdapterFactory : CallAdapter.Factory() {

    private val original by lazy {
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val wrapper = original.get(returnType, annotations, retrofit) as CallAdapter<*, *>
        return RxCallAdapterWrapper(wrapper)
    }

    private inner class RxCallAdapterWrapper<R>(
        val wrappedCallAdapter: CallAdapter<R, *>
    ) : CallAdapter<R, Single<R>> {

        @Suppress("UNCHECKED_CAST")
        override fun adapt(call: Call<R>): Single<R> {
            return (wrappedCallAdapter.adapt(call) as Single<R>).onErrorResumeNext { throwable ->
                Single.error(asRetrofitException(throwable))
            }
        }

        override fun responseType(): Type = wrappedCallAdapter.responseType()

        private fun asRetrofitException(throwable: Throwable): RetrofitException {
            if (throwable is HttpException) {
                val response = throwable.response()
                response.errorBody()?.let { responseBody ->
                    try {
                        val errorResponse = responseBody.string()
                        val serverErrorResponse = convertErrorToResponse(errorResponse)
                        return if (serverErrorResponse != null) RetrofitException.serverError(
                            serverErrorResponse
                        ) else RetrofitException.httpError(response)
                    } catch (e: IOException) {
                        Log.e(JSON_ERR, e.message)
                    }
                }
            }

            if (throwable is IOException) return RetrofitException.networkError(throwable)
            return RetrofitException.unknownError(throwable)

        }

        private fun convertErrorToResponse(errorString: String): BaseErrorResponse? {
            val gson = GsonBuilder().create()
            return try {
                gson.fromJson(errorString, BaseErrorResponse::class.java)
            } catch (exc: JsonSyntaxException) {
                Log.e(JSON_ERR, exc.message)
                null
            }
        }


    }

    companion object {
        const val JSON_ERR = "REMOTE_ERROR"

        fun create():CallAdapter.Factory = RxHandlingCallAdapterFactory()
    }
}
