package com.vannhat.mvvm_base.exercutor

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class RxExecutor(
    private val executorThread: ExecutorThread,
    private val postExecutionThread: PostExecutionThread
) {
    private val compositeDisposable = CompositeDisposable()

    fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        compositeDisposable.clear()
    }

    protected open fun getSubscribeOnScheduler() = Schedulers.from(executorThread)

    protected open fun getObserveOnScheduler() = postExecutionThread.scheduler
}
