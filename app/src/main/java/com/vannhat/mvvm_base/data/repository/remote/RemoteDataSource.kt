package com.vannhat.mvvm_base.data.repository.remote

import android.content.Context
import com.vannhat.mvvm_base.BuildConfig
import com.vannhat.mvvm_base.data.repository.remote.api.WeatherApi
import com.vannhat.mvvm_base.data.repository.remote.response.CurrentWeatherResponse
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val context: Context,
    private val api: WeatherApi
) {

    fun getCurrentWeatherByCity(cityName: String): Single<CurrentWeatherResponse> {
        return api.getCurrentWeatherByCity(cityName, BuildConfig.API_KEY, "metric")
    }

}
