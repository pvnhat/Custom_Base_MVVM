package com.vannhat.mvvm_base.data.repository.remote

import com.google.gson.Gson
import com.vannhat.mvvm_base.data.repository.remote.middleware.RxHandlingCallAdapterFactory
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {
    companion object {

        private const val CONNECTION_TIMEOUT = 60L

        fun <T> generator(
            baseUrl: String,
            serviceClass: Class<T>,
            gson: Gson,
            authenticator: Authenticator?,
            interceptors: Array<HttpLoggingInterceptor>
        ): T {
            val okHttpClientBuilder = OkHttpClient().newBuilder()

            for (interceptor in interceptors) {
                okHttpClientBuilder.addInterceptor(interceptor)
            }
            okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)

            authenticator?.let {
                okHttpClientBuilder.authenticator(authenticator)
            }
            val builder = Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxHandlingCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))

            return builder.client(okHttpClientBuilder.build()).build().create(serviceClass)
        }
    }
}
