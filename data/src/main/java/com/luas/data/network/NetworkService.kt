package com.luas.data.network

import com.luas.data.models.StopInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("get.ashx")
    suspend fun getStopInfo(@Query("stop") stop: String?): StopInfo
}