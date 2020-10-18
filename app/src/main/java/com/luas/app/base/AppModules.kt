package com.luas.app.base

import android.app.Application
import com.luas.app.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    private val viewModels = module {
        viewModel { MainViewModel() }
    }

    fun buildModules(application: Application): List<Module> {
        val resourcesModule = module {
            single { ContextAppResources(application) as AppResources }
        }
        return listOf(
            viewModels,
            resourcesModule
        )
    }
}