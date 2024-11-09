package dev.sunnat629.kDateTimeX

/**
 * Contains functions for comparing date and time.
 */
object TimeComparator {

    /**
     * Checks if a given time is in the future.
     *
     * @param timestamp The timestamp in epoch seconds.
     * @return True if the timestamp is in the future, false otherwise.
     */
    fun isFutureTime(timestamp: Long?): Boolean {
        return timestamp?.let { it.toInstant() > TimeProvider.nowInstant() } ?: false
    }

    /**
     * Checks if the current time is within the given start and end times.
     *
     * @param startTime The start time in epoch seconds.
     * @param endTime The end time in epoch seconds.
     * @return True if the current time is between start and end times, false otherwise.
     */
    fun isWithinTimeRange(startTime: Long?, endTime: Long?): Boolean {
        val currentEpoch = TimeProvider.nowEpochSeconds()
        return when {
            startTime == null || startTime == 0L -> false
            endTime == null -> currentEpoch >= startTime
            else -> currentEpoch in startTime..endTime
        }
    }

    /**
     * Checks if a time period has already ended.
     *
     * @param startTime The start time in epoch seconds.
     * @param endTime The end time in epoch seconds.
     * @return True if the time period has ended, false otherwise.
     */
    fun isTimePeriodOver(startTime: Long?, endTime: Long?): Boolean? {
        if (startTime == null || endTime == null) return null
        val currentEpoch = TimeProvider.nowEpochSeconds()
        return currentEpoch > endTime && endTime > startTime && !isWithinTimeRange(startTime, endTime)
    }
}