package com.vannhat.mvvm_base.data.repository.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vannhat.mvvm_base.data.model.remote.Temperature
import com.vannhat.mvvm_base.data.model.remote.Weather

class CurrentWeatherResponse(
    @SerializedName("weather")
    @Expose
    val weathers: List<Weather>?,

    @SerializedName("main")
    @Expose
    val temperature: Temperature?,

    @SerializedName("timezone")
    @Expose
    val timeZone: Int?,

    @SerializedName("id")
    @Expose
    val cityId: Int?,

    @SerializedName("name")
    @Expose
    val cityName: String,

    @SerializedName("cod")
    @Expose
    val cityCode: Int?
)
