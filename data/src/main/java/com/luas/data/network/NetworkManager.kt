package com.luas.data.network

import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


object NetworkManager {

    private const val BASE_URL =
        "https://luasforecasts.rpa.ie/xml/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(TikXmlConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newUrl = chain.request().url
                        .newBuilder()
                        .addQueryParameter("action", "forecast")
                        .addQueryParameter("encrypt", "false")
                        .build()
                    val request = chain.request().newBuilder().url(newUrl).build()
                    chain.proceed(request)
                }
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build()
        )
        .build()

    val networkService: NetworkService = retrofit.create(NetworkService::class.java)
}