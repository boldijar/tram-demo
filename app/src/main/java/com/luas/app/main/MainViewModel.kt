package com.luas.app.main

import androidx.lifecycle.MutableLiveData
import com.luas.app.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    val helloWorld = MutableLiveData("Hi World From Data Binding")

    fun clicked() {
        helloWorld.value = "Hello again."
    }
}