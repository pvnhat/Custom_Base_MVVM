package com.vannhat.mvvm_base.data.repository

import com.vannhat.mvvm_base.data.model.local.WeatherEntity
import com.vannhat.mvvm_base.data.repository.remote.response.CurrentWeatherResponse
import io.reactivex.Maybe
import io.reactivex.Single

interface WeatherRepository {
    fun getCurrentWeatherByCity(cityName: String): Single<CurrentWeatherResponse>

    fun getOfflineWeather(): Maybe<List<WeatherEntity>>

    fun saveOfflineWeather(weatherEntity: WeatherEntity): Single<Long>
}
