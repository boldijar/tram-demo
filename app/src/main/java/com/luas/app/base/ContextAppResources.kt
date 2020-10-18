package com.luas.app.base

import android.app.Application
import androidx.core.content.ContextCompat

class ContextAppResources(private val application: Application) : AppResources {
    override fun getColor(res: Int): Int {
        return ContextCompat.getColor(application, res)
    }

    override fun getString(res: Int): String {
        return application.getString(res)
    }
}