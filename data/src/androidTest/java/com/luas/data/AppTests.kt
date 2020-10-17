package com.luas.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.luas.data.models.Stops
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AppTests {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.luas.data.test", appContext.packageName)
    }

    @Test
    fun testStopLogic() {
        testCalendar(0, 0, Stops.Marlborough)
        testCalendar(6, 0, Stops.Marlborough)
        testCalendar(12, 0, Stops.Marlborough)
        testCalendar(12, 1, Stops.Stillorgan)
        testCalendar(18, 0, Stops.Stillorgan)
        testCalendar(23, 59, Stops.Stillorgan)
    }

    private fun testCalendar(hour: Int, minute: Int, wantedStop: Stops) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        assertEquals(Utils.getWantedStop(calendar), wantedStop)
    }

}