package com.vannhat.mvvm_base.feature.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.vannhat.mvvm_base.R
import com.vannhat.mvvm_base.data.model.ProcessState
import com.vannhat.mvvm_base.data.repository.remote.error.RetrofitException
import com.vannhat.mvvm_base.di.viewModelProvide
import com.vannhat.mvvm_base.feature.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private val viewModel: HomeViewModel by lazy {
        viewModelProvide<HomeViewModel>(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        handleEvents()
        observer()
    }

    private fun observer() {
        viewModel.weatherMutableLiveData.observe(this, Observer { state ->
            when (state.status) {
                is ProcessState.Success -> tv_weather.text = state?.data?.weathers?.first()?.type
                is ProcessState.Error -> {
                    if (state?.throwable !is RetrofitException) return@Observer
                    else {
                        tv_weather.text = getString(R.string.connect_error)
                    }
                }
            }
        })

        viewModel.offlineWeatherMutableLiveData.observe(this, Observer { state ->
            when (state.status) {
                is ProcessState.Success -> {
                    Log.d("cccc", "c" + state.data?.size)
                    tv_offline_weather.text = state.data?.first()?.sky
                }

                is ProcessState.Error -> {
                    Log.d("cccc", "online" + state.throwable)
                    tv_offline_weather.text = state.throwable?.toString()
                }
            }
        })
    }

    private fun handleEvents() {

    }

    private fun initView() {
        viewModel.getCurrentWeather("danang")
        viewModel.getOfflineLatestWeather()

    }
}
