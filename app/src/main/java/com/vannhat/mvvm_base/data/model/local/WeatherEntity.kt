package com.vannhat.mvvm_base.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LatestWeather")
class WeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "sky")
    val sky: String?,

    @ColumnInfo(name = "city")
    val city: String?,

    @ColumnInfo(name = "temperature")
    val temperature: Double
)