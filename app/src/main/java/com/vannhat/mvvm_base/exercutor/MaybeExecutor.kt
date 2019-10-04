package com.vannhat.mvvm_base.exercutor

import io.reactivex.Maybe

class MaybeExecutor<T> constructor(
    executorThread: ExecutorThread,
    postExecutionThread: PostExecutionThread
) :
    RxExecutor(executorThread, postExecutionThread) {

    fun execute(input: Maybe<T>, observer: CustomMaybeObserver<T>) {
        val observable = input.subscribeOn(getSubscribeOnScheduler())
            .observeOn(getObserveOnScheduler())
            .doOnComplete(observer.onCompleteAction())
            .doOnSubscribe(observer.onSubscribeConsumer())
            .doAfterTerminate(observer.doAfterTerminateAction())
            .doFinally(observer.doFinallyAction())
        subscribe(observable.subscribe(observer.getSuccessConsumer(), observer.onErrorConsumer()))
    }
}
