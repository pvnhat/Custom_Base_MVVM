package com.vannhat.mvvm_base.di

import android.app.Application
import android.content.Context
import com.vannhat.mvvm_base.exercutor.ExecutorThread
import com.vannhat.mvvm_base.exercutor.PostExecutionThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideThreadExecutor(): ExecutorThread {
        return ExecutorThread()
    }

    @Singleton
    @Provides
    fun providePostExecutionThread(): PostExecutionThread {
        return PostExecutionThread()
    }
}
