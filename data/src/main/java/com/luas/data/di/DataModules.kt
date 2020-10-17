package com.luas.data.di

import com.luas.data.network.ForecastRepository
import com.luas.data.network.NetworkManager
import org.koin.dsl.module

object DataModules {
    private val dataModels = module {
        single { NetworkManager().networkService }
        single { ForecastRepository(get()) }
    }

    val modules =
        listOf(
            dataModels
        )
}