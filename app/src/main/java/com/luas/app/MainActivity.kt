package com.luas.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luas.data.network.NetworkManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val result=NetworkManager.networkService.getStopInfo("MAR")
            result.createdDate
        }
    }
}