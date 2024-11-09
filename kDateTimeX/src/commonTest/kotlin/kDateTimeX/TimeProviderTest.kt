import dev.sunnat629.kDateTimeX.TimeProvider
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TimeProviderTest {

    @Test
    fun testNowInstant() {
        val instant1 = TimeProvider.nowInstant()
        // Wait a small amount of time to ensure the time advances
        val instant2 = TimeProvider.nowInstant()
        assertTrue(instant2 >= instant1, "nowInstant() should return the current time or later")
    }

    @Test
    fun testNowEpochSeconds() {
        val epochSeconds1 = TimeProvider.nowEpochSeconds()
        // Wait a small amount of time to ensure the time advances
        val epochSeconds2 = TimeProvider.nowEpochSeconds()
        assertTrue(epochSeconds2 >= epochSeconds1, "nowEpochSeconds() should return the current epoch seconds or later")
    }

    @Test
    fun testNowLocalDateTime() {
        val localDateTime: LocalDateTime = TimeProvider.nowLocalDateTime()
        val instant: Instant = TimeProvider.nowInstant()
        val expectedLocalDateTime: LocalDateTime = instant.toLocalDateTime(TimeProvider.systemTimeZone)
        assertEquals(expectedLocalDateTime.date, localDateTime.date, "Dates should match")
        // Time may not be exactly equal due to time elapsed, so we compare up to minutes
        assertEquals(expectedLocalDateTime.hour, localDateTime.hour, "Hours should match")
        assertEquals(expectedLocalDateTime.minute, localDateTime.minute, "Minutes should match")
    }
}