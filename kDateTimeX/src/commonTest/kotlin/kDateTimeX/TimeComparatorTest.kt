import dev.sunnat629.kDateTimeX.TimeComparator.isFutureTime
import dev.sunnat629.kDateTimeX.TimeComparator.isTimePeriodOver
import dev.sunnat629.kDateTimeX.TimeComparator.isWithinTimeRange
import dev.sunnat629.kDateTimeX.TimeProvider
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TimeComparatorTest {

    @Test
    fun testIsFutureTime() {
        val futureTimestamp = TimeProvider.nowEpochSeconds() + 3600 // 1 hour ahead
        assertTrue(isFutureTime(futureTimestamp))

        val pastTimestamp = TimeProvider.nowEpochSeconds() - 3600 // 1 hour ago
        assertFalse(isFutureTime(pastTimestamp))
    }

    @Test
    fun testIsWithinTimeRange() {
        val now = TimeProvider.nowEpochSeconds()
        val startTime = now - 3600 // 1 hour ago
        val endTime = now + 3600   // 1 hour ahead

        assertTrue(isWithinTimeRange(startTime, endTime))
        assertFalse(isWithinTimeRange(now + 7200, endTime)) // Now + 2 hours
    }

    @Test
    fun testIsWithinTimeRangeNullStartTime() {
        val endTime = TimeProvider.nowEpochSeconds() + 3600
        assertFalse(isWithinTimeRange(null, endTime))
    }

    @Test
    fun testIsTimePeriodOver() {
        val now = TimeProvider.nowEpochSeconds()
        val startTime = now - 7200 // 2 hours ago
        val endTime = now - 3600   // 1 hour ago

        assertTrue(isTimePeriodOver(startTime, endTime) ?: false)
    }

    @Test
    fun testIsTimePeriodOverOngoing() {
        val now = TimeProvider.nowEpochSeconds()
        val startTime = now - 3600 // 1 hour ago
        val endTime = now + 3600   // 1 hour ahead
        assertFalse(isTimePeriodOver(startTime, endTime) ?: true)
    }

    @Test
    fun testIsTimePeriodOverNullValues() {
        assertNull(isTimePeriodOver(null, null))
        assertNull(isTimePeriodOver(1000L, null))
        assertNull(isTimePeriodOver(null, 1000L))
    }
}