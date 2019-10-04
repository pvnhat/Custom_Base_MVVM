package com.vannhat.mvvm_base.data.repository.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseErrorResponse(
    @SerializedName("cod")
    @Expose
    val errorCode: Int?,
    @SerializedName("message")
    @Expose
    val errorMessage: String?
)
