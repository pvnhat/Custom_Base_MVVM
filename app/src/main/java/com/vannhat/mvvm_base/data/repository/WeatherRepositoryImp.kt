package com.vannhat.mvvm_base.data.repository

import com.vannhat.mvvm_base.data.model.local.WeatherEntity
import com.vannhat.mvvm_base.data.repository.local.LocalDataSource
import com.vannhat.mvvm_base.data.repository.remote.RemoteDataSource
import com.vannhat.mvvm_base.data.repository.remote.response.CurrentWeatherResponse
import io.reactivex.Maybe
import io.reactivex.Single

class WeatherRepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : WeatherRepository {
    override fun saveOfflineWeather(weatherEntity: WeatherEntity): Single<Long> {
        return localDataSource.saveLatestOfflineWeather(weatherEntity)
    }

    override fun getOfflineWeather(): Maybe<List<WeatherEntity>> {
        return localDataSource.getLatestWeather()
    }

    override fun getCurrentWeatherByCity(cityName: String): Single<CurrentWeatherResponse> {
        return remoteDataSource.getCurrentWeatherByCity(cityName)
    }
}
