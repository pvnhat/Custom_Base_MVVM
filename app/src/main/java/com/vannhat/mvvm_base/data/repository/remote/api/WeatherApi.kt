package com.vannhat.mvvm_base.data.repository.remote.api

import com.vannhat.mvvm_base.data.repository.remote.response.CurrentWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("weather")
    fun getCurrentWeatherByCity(@Query("q") city: String?,
        @Query("appid") apiKey: String,
        @Query("units") unit: String?): Single<CurrentWeatherResponse>
}
