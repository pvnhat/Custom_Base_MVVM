package com.vannhat.mvvm_base.data.model.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather(
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("main")
    @Expose
    val type: String?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("icon")
    @Expose
    val icon: String?
)
