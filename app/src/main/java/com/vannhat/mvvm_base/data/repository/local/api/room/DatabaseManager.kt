package com.vannhat.mvvm_base.data.repository.local.api.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vannhat.mvvm_base.data.model.local.WeatherEntity
import com.vannhat.mvvm_base.data.repository.local.api.room.DatabaseManager.Companion.DATABASE_VERSION

@Database(entities = [WeatherEntity::class], version = DATABASE_VERSION)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun getLatestWeatherDao(): DatabaseDao

    companion object {
        const val DATABASE_VERSION = 1
    }
}
