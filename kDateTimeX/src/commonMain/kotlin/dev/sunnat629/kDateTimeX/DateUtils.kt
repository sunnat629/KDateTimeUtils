package dev.sunnat629.kDateTimeX

import kotlinx.datetime.*

/**
 * Contains date-related utility functions.
 */
object DateUtils {

    /**
     * Calculates the number of days between two dates.
     *
     * @param startDate The start [LocalDate].
     * @param endDate The end [LocalDate].
     * @return The number of days between the two dates.
     */
    fun daysBetween(startDate: LocalDate, endDate: LocalDate): Int {
        return startDate.daysUntil(endDate)
    }

    /**
     * Adds a specified number of days to a timestamp.
     *
     * @param timestamp The timestamp in epoch seconds.
     * @param daysToAdd The number of days to add.
     * @return The new timestamp in epoch seconds.
     */
    fun addDaysToTimestamp(timestamp: Long, daysToAdd: Int): Long {
        val instant = timestamp.toInstant().plus(daysToAdd, DateTimeUnit.DAY, TimeZone.UTC)
        return instant.epochSeconds
    }

    /**
     * Checks if a year is a leap year.
     *
     * @param year The year to check.
     * @return True if the year is a leap year, false otherwise.
     */
    fun isLeapYear(year: Int): Boolean {
        return floorMod(year, 4) == 0 && (year % 100 != 0 || year % 400 == 0)
    }

    /**
     * Gets the start of the day for a given timestamp.
     *
     * @param timestamp The timestamp in epoch seconds.
     * @return The timestamp representing the start of the day in epoch seconds.
     */
    fun getStartOfDay(timestamp: Long): Long {
        val localDate = timestamp.toLocalDateTime().date
        val startOfDay = localDate.atStartOfDayIn(TimeProvider.utcTimeZone)
        return startOfDay.epochSeconds
    }

    /**
     * Gets the end of the day for a given timestamp.
     *
     * @param timestamp The timestamp in epoch seconds.
     * @return The timestamp representing the end of the day in epoch seconds.
     */
    fun getEndOfDay(timestamp: Long): Long {
        val localDate = timestamp.toLocalDateTime().date
        val endOfDay = localDate.plus(1, DateTimeUnit.DAY)
            .atStartOfDayIn(TimeProvider.utcTimeZone)
            .minus(1, DateTimeUnit.SECOND)
        return endOfDay.epochSeconds
    }
}