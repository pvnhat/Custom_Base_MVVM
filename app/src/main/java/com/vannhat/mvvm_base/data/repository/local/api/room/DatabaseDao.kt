package com.vannhat.mvvm_base.data.repository.local.api.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vannhat.mvvm_base.data.model.local.WeatherEntity
import io.reactivex.Maybe

/**
 * To query
 */
@Dao
abstract class DatabaseDao {

    @Query("SELECT * FROM LatestWeather ORDER BY id DESC LIMIT :numberOfResult")
    abstract fun getLatestWeather(numberOfResult: Int = 1): Maybe<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveLatestWeather(weatherEntity: WeatherEntity): Long
}
