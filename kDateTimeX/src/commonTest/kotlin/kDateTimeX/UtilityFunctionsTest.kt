import dev.sunnat629.kDateTimeX.TimeProvider
import dev.sunnat629.kDateTimeX.UtilityFunctions
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

class UtilityFunctionsTest {

    @Test
    fun testFormatTimeDifference() {
        assertEquals("Last updated now", UtilityFunctions.formatTimeDifference(0L))
        assertEquals("Last updated 1 second ago", UtilityFunctions.formatTimeDifference(1L))
        assertEquals("Last updated 5 seconds ago", UtilityFunctions.formatTimeDifference(5L))
    }

    @Test
    fun testCalculateDurationInMinutes() {
        val startTime = 1638316800L // 2021-12-01 00:00:00 UTC
        val endTime = 1638318600L   // 2021-12-01 00:30:00 UTC
        assertEquals(30, UtilityFunctions.calculateDurationInMinutes(startTime, endTime))
    }

    @Test
    fun testConvertNanosecondsToMilliseconds() {
        assertEquals(1.0, UtilityFunctions.convertNanosecondsToMilliseconds(1_000_000L))
        assertEquals(0.001, UtilityFunctions.convertNanosecondsToMilliseconds(1_000L))
        assertEquals(0.0, UtilityFunctions.convertNanosecondsToMilliseconds(0L))
    }

    @Test
    fun testDelayForDuration() = runTest {
//        val duration = 3.seconds
//        val startTime = TimeProvider.nowEpochSeconds()
//        UtilityFunctions.delayForDuration(duration)
//        val endTime = TimeProvider.nowEpochSeconds()
//        val elapsedTime = endTime - startTime
//        println("endTime: $endTime || startTime: $startTime")
//        // Allow some tolerance due to execution time
//        assertTrue(elapsedTime >= 2, "Delay should be at least 2 seconds")
    }
}