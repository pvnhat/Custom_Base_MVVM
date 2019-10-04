package com.vannhat.mvvm_base.exercutor

import androidx.annotation.NonNull
import io.reactivex.functions.Consumer

abstract class CustomSingleObserver<T> : CustomObserver() {

    internal fun getSuccessConsumer(): Consumer<T> {
        return Consumer {
            this.onSuccess(it)
        }
    }

    open fun onSuccess(@NonNull data: T) {}
}
