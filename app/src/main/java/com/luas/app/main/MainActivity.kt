package com.luas.app.main

import com.luas.app.R
import com.luas.app.base.BaseActivity
import com.luas.app.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel by viewModel<MainViewModel>()

}