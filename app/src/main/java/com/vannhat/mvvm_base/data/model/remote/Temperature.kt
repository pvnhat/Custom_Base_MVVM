package com.vannhat.mvvm_base.data.model.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Temperature(
    @SerializedName("temp")
    @Expose
    val temperature: Double?,

    @SerializedName("pressure")
    @Expose
    val pressure: Int?,

    @SerializedName("humidity")
    @Expose
    val humidity: Int?,

    @SerializedName("temp_min")
    @Expose
    val minTemperature: Double?,

    @SerializedName("temp_max")
    @Expose
    val maxTemperature: Double?
)
