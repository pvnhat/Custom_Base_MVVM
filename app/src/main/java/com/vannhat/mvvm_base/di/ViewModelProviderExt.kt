package com.vannhat.mvvm_base.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

// for Activity declaration
inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvide(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

// for Fragment declaration
inline fun <reified VM : ViewModel> Fragment.viewModelProvide(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)