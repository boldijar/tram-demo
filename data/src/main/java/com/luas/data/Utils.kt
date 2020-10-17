package com.luas.data

import com.luas.data.models.Stops
import java.util.*

object Utils {
    fun getWantedStop(calendar: Calendar): Stops {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        if (hour <= 11) {
            return Stops.Marlborough
        }
        val minute = calendar.get(Calendar.MINUTE)
        return if (hour + minute <= 12) {
            Stops.Marlborough
        } else {
            Stops.Stillorgan
        }
    }
}