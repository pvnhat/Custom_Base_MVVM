package com.vannhat.mvvm_base.data.repository.local

import com.vannhat.mvvm_base.data.model.local.WeatherEntity
import com.vannhat.mvvm_base.data.repository.local.api.DatabaseApi
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val databaseApi: DatabaseApi) {

    fun getLatestWeather(): Maybe<List<WeatherEntity>> {
        return databaseApi.offlineLatestWeather().getLatestWeather()
    }

    fun saveLatestOfflineWeather(weatherEntity: WeatherEntity): Single<Long> {
        return Single.create { emitter ->
            emitter.onSuccess(databaseApi.offlineLatestWeather().saveLatestWeather(weatherEntity))
        }
    }
}
