package dev.sunnat629.kDateTimeX

import kotlin.time.Duration

/**
 * Contains general utility functions.
 */
object UtilityFunctions {

    /**
     * Formats the time difference for display, e.g., "Last updated X seconds ago".
     *
     * @param diffSeconds The difference in seconds.
     * @return The formatted time difference string.
     */
    fun formatTimeDifference(diffSeconds: Long): String {
        return when (diffSeconds) {
            0L -> "now"
            1L -> "1 second ago"
            else -> "$diffSeconds seconds ago"
        }
    }

    /**
     * Calculates the duration between two timestamps in minutes.
     *
     * @param startTime The start time in epoch seconds.
     * @param endTime The end time in epoch seconds.
     * @return The duration in minutes.
     */
    fun calculateDurationInMinutes(startTime: Long, endTime: Long): Int {
        return ((endTime - startTime) / 60).toInt()
    }

    /**
     * Converts nanoseconds to milliseconds with fractional part.
     *
     * @param nanoseconds The number of nanoseconds.
     * @return The milliseconds as a [Double] value.
     */
    fun convertNanosecondsToMilliseconds(nanoseconds: Long): Double {
        return nanoseconds / 1_000_000.0
    }

    /**
     * Suspends execution for a given duration.
     *
     * @param duration The [Duration] to suspend.
     */
    suspend fun delayForDuration(duration: Duration) {
        kotlinx.coroutines.delay(duration)
    }
}