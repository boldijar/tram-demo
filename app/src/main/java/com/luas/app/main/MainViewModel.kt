package com.luas.app.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luas.app.base.BaseViewModel
import com.luas.data.Utils
import com.luas.data.models.StopInfo
import com.luas.data.network.ForecastRepository
import com.luas.data.network.base.ApiResponse
import com.luas.data.network.base.LiveEvent
import kotlinx.coroutines.launch
import org.koin.core.inject
import java.util.*

class MainViewModel : BaseViewModel() {

    private val forecastRepository: ForecastRepository by inject()

    private val _command = LiveEvent<Command>()
    val command: LiveData<Command> = _command

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    val helloWorld = MutableLiveData("Hi World From Data Binding")

    private fun load() {
        _loading.value = true
        viewModelScope.launch {
            val stop = Utils.getWantedStop(Calendar.getInstance()).value
            val stopInfoResponse = forecastRepository.loadForecast(stop)
            _loading.postValue(false)
            when (stopInfoResponse) {
                is ApiResponse.Error -> {
                    _command.postValue(Command.ShowError(stopInfoResponse.errorMessage))
                }
                is ApiResponse.Success -> {
                    _command.postValue(Command.ShowStop(stopInfoResponse.result))
                }
            }
        }
    }

    fun clicked() {
        load()
    }

    sealed class Command {
        class ShowStop(val stopInfo: StopInfo) : Command()
        class ShowError(val message: String) : Command()
    }
}