package com.vannhat.mvvm_base.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vannhat.mvvm_base.feature.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(viewModel:HomeViewModel): ViewModel
}
