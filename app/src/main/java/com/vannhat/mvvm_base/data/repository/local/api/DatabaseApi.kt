package com.vannhat.mvvm_base.data.repository.local.api

import com.vannhat.mvvm_base.data.repository.local.api.room.DatabaseDao

interface DatabaseApi {
    fun offlineLatestWeather(): DatabaseDao
}
