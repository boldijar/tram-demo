package com.luas.app.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luas.app.R
import com.luas.app.base.AppResources
import com.luas.app.base.BaseViewModel
import com.luas.app.main.stops.TramItemViewModel
import com.luas.data.Utils
import com.luas.data.models.StopInfo
import com.luas.data.models.Stops
import com.luas.data.models.Tram
import com.luas.data.network.ForecastRepository
import com.luas.data.network.base.ApiResponse
import com.luas.data.network.base.LiveEvent
import kotlinx.coroutines.launch
import org.koin.core.inject
import java.util.*

class MainViewModel : BaseViewModel() {

    private val forecastRepository: ForecastRepository by inject()
    private val appResources: AppResources by inject()

    private val _command = LiveEvent<Command>()
    val command: LiveData<Command> = _command

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _showEmpty = MutableLiveData(false)
    val showEmpty: LiveData<Boolean> = _showEmpty


    fun load() {
        _loading.value = true
        viewModelScope.launch {
            val stop = Utils.getWantedStop(Calendar.getInstance())
            val stopInfoResponse = forecastRepository.loadForecast(stop.value)
            _loading.postValue(false)
            _showEmpty.postValue(false)
            when (stopInfoResponse) {
                is ApiResponse.Error -> {
                    _command.postValue(Command.ShowError(stopInfoResponse.errorMessage))
                }
                is ApiResponse.Success -> {
                    val viewModels = stopInfoToViewModels(stop, stopInfoResponse.result)
                    _command.postValue(Command.ShowTram(viewModels))
                    _showEmpty.postValue(viewModels.isEmpty())
                }
            }
        }
    }

    private fun stopInfoToViewModels(
        stop: Stops,
        stopInfo: StopInfo
    ): MutableList<TramItemViewModel> {
        val direction = stopInfo.directions.firstOrNull { it.directionName == stop.direction }
        var count = 0
        return direction?.listOfTrams?.map {
            val color =
                appResources.getColor(if (count % 2 == 0) R.color.colorPrimary else R.color.colorAccent)
            count++
            tramToViewModel(it, color)
        }?.toMutableList() ?: mutableListOf()
    }


    private fun tramToViewModel(it: Tram, color: Int): TramItemViewModel {
        val time = if (it.dueMins.toIntOrNull() == null) {
            it.dueMins
        } else {
            "${it.dueMins} ${appResources.getString(R.string.mins)}"
        }
        return TramItemViewModel(
            name = it.destination,
            time = time,
            color = color
        )
    }

    sealed class Command {
        class ShowTram(val viewModels: MutableList<TramItemViewModel>) : Command()
        class ShowError(val message: String) : Command()
    }
}