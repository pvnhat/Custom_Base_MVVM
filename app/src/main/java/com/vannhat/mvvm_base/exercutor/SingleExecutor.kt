package com.vannhat.mvvm_base.exercutor

import io.reactivex.Single

class SingleExecutor<T> constructor(
    executorThread: ExecutorThread,
    postExecutionThread: PostExecutionThread
) :
    RxExecutor(executorThread, postExecutionThread) {

    fun execute(input: Single<T>, observer: CustomSingleObserver<T>) {
        val observable = input.subscribeOn(getSubscribeOnScheduler())
            .observeOn(getObserveOnScheduler())
            .doOnSubscribe(observer.onSubscribeConsumer())
            .doAfterTerminate(observer.doAfterTerminateAction())
            .doFinally(observer.doFinallyAction())
        subscribe(observable.subscribe(observer.getSuccessConsumer(), observer.onErrorConsumer()))
    }
}
