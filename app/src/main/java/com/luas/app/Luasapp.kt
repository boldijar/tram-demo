package com.luas.app

import android.app.Application
import com.luas.app.base.AppModules
import com.luas.data.di.DataModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class Luasapp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        startKoin {
            val modulesList = mutableListOf<Module>()
            modulesList.addAll(DataModules.modules)
            modulesList.addAll(AppModules.buildModules(this@Luasapp))
            modules(modulesList)
        }
    }
}