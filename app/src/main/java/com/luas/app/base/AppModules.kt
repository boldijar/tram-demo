package com.luas.app.base

import com.luas.app.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {

    private val viewModels = module {
        viewModel { MainViewModel() }
    }

    val modules =
        listOf(
            viewModels
        )
}