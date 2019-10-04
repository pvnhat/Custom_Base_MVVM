package com.vannhat.mvvm_base.di

import com.vannhat.mvvm_base.di.scope.ActivityScope
import com.vannhat.mvvm_base.feature.home.HomeActivity
import com.vannhat.mvvm_base.feature.home.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    @ActivityScope
    internal abstract fun bindHomeActivity(): HomeActivity
}
