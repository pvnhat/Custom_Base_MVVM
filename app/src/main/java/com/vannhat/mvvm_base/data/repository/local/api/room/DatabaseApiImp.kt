package com.vannhat.mvvm_base.data.repository.local.api.room

import com.vannhat.mvvm_base.data.repository.local.api.DatabaseApi

class DatabaseApiImp(private val databaseManager: DatabaseManager) : DatabaseApi {

    override fun offlineLatestWeather(): DatabaseDao {
        return databaseManager.getLatestWeatherDao()
    }
}
