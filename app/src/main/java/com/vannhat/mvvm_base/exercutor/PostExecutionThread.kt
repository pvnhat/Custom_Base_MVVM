package com.vannhat.mvvm_base.exercutor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class PostExecutionThread {
    val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
