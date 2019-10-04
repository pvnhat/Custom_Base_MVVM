package com.vannhat.mvvm_base.feature.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vannhat.mvvm_base.data.model.ProcessState
import com.vannhat.mvvm_base.data.model.local.WeatherEntity
import com.vannhat.mvvm_base.data.repository.WeatherRepository
import com.vannhat.mvvm_base.data.repository.remote.response.CurrentWeatherResponse
import com.vannhat.mvvm_base.exercutor.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val repository: WeatherRepository,
    private val executorThread: ExecutorThread,
    private val postExecutionThread: PostExecutionThread
) : ViewModel() {
    var weatherMutableLiveData = MutableLiveData<ProcessState<CurrentWeatherResponse>>()
    var offlineWeatherMutableLiveData = MutableLiveData<ProcessState<List<WeatherEntity>>>()

    fun getCurrentWeather(cityName: String) {
        val singleExecutor =
            SingleExecutor<CurrentWeatherResponse>(executorThread, postExecutionThread)
        singleExecutor.execute(
            repository.getCurrentWeatherByCity(cityName),
            object : CustomSingleObserver<CurrentWeatherResponse>() {
                override fun onSuccess(data: CurrentWeatherResponse) {
                    super.onSuccess(data)
                    weatherMutableLiveData.value = ProcessState.success(data)

                    saveOfflineWeather(data)
                }

                override fun onError(throwable: Throwable) {
                    super.onError(throwable)
                    weatherMutableLiveData.value = ProcessState.error(throwable)
                }
            })
    }

    private fun saveOfflineWeather(currentWeatherResponse: CurrentWeatherResponse) {
        val singleExecutor = SingleExecutor<Long>(executorThread, postExecutionThread)

        singleExecutor.execute(
            repository.saveOfflineWeather(
                WeatherEntity(
                    currentWeatherResponse.cityId ?: 1,
                    currentWeatherResponse.weathers?.first()?.type,
                    currentWeatherResponse.cityName,
                    currentWeatherResponse.temperature?.temperature ?: 0.toDouble()
                )
            ), object : CustomSingleObserver<Long>() {
                override fun onSuccess(data: Long) {
                    super.onSuccess(data)
                    Log.d("cccc","count$data")
                }

                override fun onError(throwable: Throwable) {
                    super.onError(throwable)
                    Log.d("cccc","count$throwable")
                }
            })
    }

    fun getOfflineLatestWeather() {
        val maybeObserver =
            MaybeExecutor<List<WeatherEntity>>(executorThread, postExecutionThread)
        maybeObserver.execute(repository.getOfflineWeather(),
            object : CustomMaybeObserver<List<WeatherEntity>>() {
                override fun onSuccess(data: List<WeatherEntity>) {
                    super.onSuccess(data)
                    Log.d("cccc", "c" + data.size)
                    offlineWeatherMutableLiveData.value = ProcessState.success(data)
                }

                override fun onError(throwable: Throwable) {
                    super.onError(throwable)
                    Log.d("cccc", "cee$throwable")
                    offlineWeatherMutableLiveData.value = ProcessState.error(throwable)
                }
            })
    }


}
