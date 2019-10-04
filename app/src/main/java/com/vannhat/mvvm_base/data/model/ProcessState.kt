package com.vannhat.mvvm_base.data.model

class ProcessState<out T>(
    val status: Status,
    val data: T? = null,
    val throwable: Throwable? = null,
    val message: String? = null
) {

    companion object {
        fun <T> success(data: T? = null): ProcessState<T> {
            return ProcessState(Success(), data)
        }

        fun <T> error(throwable: Throwable?, message: String? = null): ProcessState<T> {
            return ProcessState(Error(), throwable = throwable, message = message)
        }

        fun <T> loading(): ProcessState<T> {
            return ProcessState(Loading())
        }

        fun <T> finish(): ProcessState<T> {
            return ProcessState(Finish())
        }
    }

    class Loading : Status
    class Error : Status
    class Success : Status
    class Finish : Status
}

interface Status