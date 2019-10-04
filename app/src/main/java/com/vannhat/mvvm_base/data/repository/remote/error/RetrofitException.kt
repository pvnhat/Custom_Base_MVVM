package com.vannhat.mvvm_base.data.repository.remote.error

import com.vannhat.mvvm_base.data.repository.remote.response.BaseErrorResponse
import retrofit2.Response

class RetrofitException(
    private val kind: Kind,
    private val serverErrorResponse: BaseErrorResponse? = null,
    private var response: Response<*>? = null,
    private val exception: Throwable? = null
) : RuntimeException() {
    companion object {
        fun httpError(response: Response<*>): RetrofitException {
            return RetrofitException(kind = Kind.HTTP, response = response)
        }

        fun serverError(serverErrorResponse: BaseErrorResponse): RetrofitException {
            return RetrofitException(kind = Kind.SERVER, serverErrorResponse = serverErrorResponse)
        }

        fun networkError(throwable: Throwable): RetrofitException {
            return RetrofitException(kind = Kind.NETWORK, exception = throwable)
        }

        fun unknownError(throwable: Throwable): RetrofitException {
            return RetrofitException(kind = Kind.UNKNOWN, exception = throwable)
        }
    }

    // Get error will implement here
}