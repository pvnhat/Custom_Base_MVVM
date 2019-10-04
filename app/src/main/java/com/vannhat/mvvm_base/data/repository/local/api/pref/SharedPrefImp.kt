package com.vannhat.mvvm_base.data.repository.local.api.pref

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vannhat.mvvm_base.data.repository.local.api.SharedPrefApi
import com.vannhat.mvvm_base.utils.BaseLink.SHARE_PREF_KEY
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.schedulers.Schedulers

class SharedPrefImp(context: Context, private val gson: Gson) : SharedPrefApi {

    private val sharedPreferences =
        context.getSharedPreferences(SHARE_PREF_KEY, Context.MODE_PRIVATE)

    override fun <T> singlePref(action: (SingleEmitter<in T>) -> Unit): Single<T> {
        return Single.create<T> { emitter ->
            action(emitter)
        }.subscribeOn(Schedulers.io())
    }

    override fun completablePref(action: (SharedPrefApi) -> Unit): Completable {
        return Completable.create { emitter ->
            try {
                action(this)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    override fun <T> put(key: String, data: T) {
        val editor = sharedPreferences.edit()
        when (data) {
            is Int -> editor.putInt(key, data)
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Long -> editor.putLong(key, data)
            is Float -> editor.putFloat(key, data)
            else -> editor.putString(key, gson.toJson(data))
        }
        editor.apply()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, type: Class<T>, default: T?): T {
        return when (type) {
            String::class.java -> sharedPreferences.getString(key, default as? String) as T
            Int::class.java -> sharedPreferences.getInt(key, default as? Int ?: 0) as T
            Boolean::class.java -> sharedPreferences.getBoolean(
                key,
                default as? Boolean ?: false
            ) as T
            Long::class.java -> sharedPreferences.getLong(key, default as? Long ?: 0L) as T
            Float::class.java -> sharedPreferences.getFloat(key, default as? Float ?: 0F) as T
            else -> gson.fromJson(sharedPreferences.getString(key, default as? String), type)
        }
    }

    override fun <T> putList(key: String, list: List<T>) {
        val editor = sharedPreferences.edit()
        editor.putString(key, gson.toJson(list))
        editor.apply()
    }

    override fun <T> getList(key: String, clazz: Class<T>): List<T> {
        val typeOfT = TypeToken.getParameterized(List::class.java, clazz).type
        return gson.fromJson<List<T>>(get(key, String::class.java), typeOfT)
    }

    override fun removeKey(key: String) {
        sharedPreferences.edit().apply {
            remove(key)
            apply()
        }
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }

}
