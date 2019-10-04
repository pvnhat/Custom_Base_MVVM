package com.vannhat.mvvm_base.data.repository.local.api

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleEmitter

interface SharedPrefApi {
    fun <T> singlePref(action: (SingleEmitter<in T>) -> Unit): Single<T>
    fun completablePref(action: (SharedPrefApi) -> Unit): Completable
    fun <T> put(key: String, data: T)
    fun <T> get(key: String, type: Class<T>, default: T? = null): T
    fun <T> putList(key: String, list: List<T>)
    fun <T> getList(key: String, clazz: Class<T>): List<T>
    fun removeKey(key: String)
    fun clear()
}
