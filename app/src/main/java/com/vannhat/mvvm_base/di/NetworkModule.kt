package com.vannhat.mvvm_base.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vannhat.mvvm_base.data.repository.remote.ServiceGenerator
import com.vannhat.mvvm_base.data.repository.remote.api.WeatherApi
import com.vannhat.mvvm_base.utils.BaseLink
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideApi(gson: Gson, loggingInterceptor: HttpLoggingInterceptor): WeatherApi {
        val interceptors = arrayOf(loggingInterceptor)
        return ServiceGenerator.generator(
            BaseLink.BASE_LINK_API,
            WeatherApi::class.java,
            gson,
            null, interceptors
        )
    }
}