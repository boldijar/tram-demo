package com.luas.data.network

import com.luas.data.models.StopInfo
import com.luas.data.network.base.ApiResponse
import com.luas.data.network.base.BaseRepository

class ForecastRepository(private val networkService: NetworkService) : BaseRepository() {

    suspend fun loadForecast(stop: String?): ApiResponse<StopInfo> = doRequest {
        networkService.getStopInfo(stop)
    }
}