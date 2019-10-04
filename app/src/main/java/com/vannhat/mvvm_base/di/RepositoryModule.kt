package com.vannhat.mvvm_base.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.vannhat.mvvm_base.BuildConfig
import com.vannhat.mvvm_base.data.repository.WeatherRepository
import com.vannhat.mvvm_base.data.repository.WeatherRepositoryImp
import com.vannhat.mvvm_base.data.repository.local.LocalDataSource
import com.vannhat.mvvm_base.data.repository.local.api.DatabaseApi
import com.vannhat.mvvm_base.data.repository.local.api.SharedPrefApi
import com.vannhat.mvvm_base.data.repository.local.api.pref.SharedPrefImp
import com.vannhat.mvvm_base.data.repository.local.api.room.DatabaseApiImp
import com.vannhat.mvvm_base.data.repository.local.api.room.DatabaseManager
import com.vannhat.mvvm_base.data.repository.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSharedPrefApi(context: Context, gson: Gson): SharedPrefApi {
        return SharedPrefImp(context, gson)
    }


    @Singleton
    @Provides
    fun provideDatabaseManager(context: Context): DatabaseManager =
        Room.databaseBuilder(
            context,
            DatabaseManager::class.java,
            BuildConfig.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideDatabaseApi(databaseManager: DatabaseManager): DatabaseApi =
        DatabaseApiImp(databaseManager)

    @Singleton
    @Provides
    fun provideWeatherRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): WeatherRepository {
        return WeatherRepositoryImp(remoteDataSource, localDataSource)
    }

}
