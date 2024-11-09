package dev.sunnat629.kDateTimeX

import kotlinx.datetime.*
import kotlin.math.absoluteValue
import kotlin.time.Duration

/**
 * Contains functions to format date and time.
 */
object TimeFormatter {

    /**
     * Formats a duration in seconds to a string in "HH:mm:ss" format.
     *
     * @param durationSeconds The duration in seconds.
     * @return The formatted duration string.
     */
    fun formatDurationSeconds(durationSeconds: Double?): String {
        if (durationSeconds == null) return ""
        val totalSeconds = durationSeconds.toLong()
        val hours = (totalSeconds / 3600).toInt().toDoubleDigitString()
        val minutes = ((totalSeconds % 3600) / 60).toInt().toDoubleDigitString()
        val seconds = (totalSeconds % 60).toInt().toDoubleDigitString()
        return "$hours:$minutes:$seconds"
    }

   
    /**
     * Formats a timestamp (in milliseconds) to a human-readable date string "YYYY-MM-DD".
     *
     * @param timestampMillis The timestamp in milliseconds.
     * @return The formatted date string.
     */
    fun formatMillisecondsToDate(timestampMillis: Long?): String {
        if (timestampMillis == null || timestampMillis < 0) return ""
        val dateTime = Instant.fromEpochMilliseconds(timestampMillis).toLocalDateTime(TimeZone.UTC)
        val year = dateTime.year.toString().padStart(4, '0')
        val month = dateTime.monthNumber.toDoubleDigitString()
        val day = dateTime.dayOfMonth.toDoubleDigitString()
        return "$year-$month-$day"
    }

    /**
     * Converts a timestamp to a formatted time string "HH:mm" or "HH:mm:ss" based on the [includeSeconds] flag.
     *
     * @param timestamp The timestamp in epoch seconds.
     * @param includeSeconds Whether to include seconds in the output.
     * @return The formatted time string, or null if timestamp is null or non-positive.
     */
    fun formatTimestampToTime(timestamp: Long?, includeSeconds: Boolean = false): String? {
        return timestamp?.takeIf { it > 0 }?.toLocalDateTime()?.let {
            println(it)
            val hour = it.hour.toDoubleDigitString()
            val minute = it.minute.toDoubleDigitString()
            val second = it.second.toDoubleDigitString()
            if (includeSeconds) "$hour:$minute:$second" else "$hour:$minute"
        }
    }

    /**
     * Formats milliseconds to a string with limited days "d HH:mm:ss.SSS".
     * Days over 9 are represented as "X".
     *
     * @param totalMilliseconds The total milliseconds.
     * @return The formatted time string.
     */
    fun formatMillisecondsWithLimitedDays(totalMilliseconds: Long): String {
        if (totalMilliseconds < 0) return "0 00:00:00.000"
        val days = (totalMilliseconds / (24 * 60 * 60 * 1000)).toInt()
        val hours = ((totalMilliseconds / (60 * 60 * 1000)) % 24).toInt().toDoubleDigitString()
        val minutes = ((totalMilliseconds / (60 * 1000)) % 60).toInt().toDoubleDigitString()
        val seconds = ((totalMilliseconds / 1000) % 60).toInt().toDoubleDigitString()
        val milliseconds = (totalMilliseconds % 1000).toInt().toTripleDigitString()
        val dayDisplay = if (days > 9) "X" else days.toString()
        return "$dayDisplay $hours:$minutes:$seconds.$milliseconds"
    }

    /**
     * Formats a [Duration] to a string in "+/-HH:mm:ss.S" format.
     *
     * @param duration The duration to format.
     * @return The formatted duration string.
     */
    fun formatDurationWithSign(duration: Duration): String {
        val sign = if (duration.isNegative()) "-" else "+"
        val totalSeconds = duration.inWholeSeconds.absoluteValue
        val hours = (totalSeconds / 3600).toInt().toDoubleDigitString()
        val minutes = ((totalSeconds % 3600) / 60).toInt().toDoubleDigitString()
        val seconds = (totalSeconds % 60).toInt().toDoubleDigitString()
        val milliseconds = ((duration.inWholeMilliseconds.absoluteValue % 1000) / 100).toInt()
        return "$sign$hours:$minutes:$seconds.$milliseconds"
    }

    // Get formatted time with optional live indicator
    fun formatTime(
        startTime: Long?,
        timeZone: TimeZone = TimeZone.currentSystemDefault(),
        isLive: Boolean? = null
    ): String? {
        if (startTime == null) return null

        val now = Clock.System.now().epochSeconds
        val currentDateTime = Clock.System.now().toLocalDateTime(timeZone)
        val targetDateTime = Instant.fromEpochSeconds(startTime).toLocalDateTime(timeZone)

        val hour = targetDateTime.hour.toDoubleDigitString()
        val minute = targetDateTime.minute.toDoubleDigitString()
        val dayOfMonth = targetDateTime.dayOfMonth.toDoubleDigitString()
        val month = targetDateTime.month.name.take(3)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val year = targetDateTime.year
        val weekday = targetDateTime.dayOfWeek.name.take(3)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

        val isToday = targetDateTime.date == currentDateTime.date
        val isYesterday = targetDateTime.date == currentDateTime.date.minus(1, DateTimeUnit.DAY)
        val isTomorrow = targetDateTime.date == currentDateTime.date.plus(1, DateTimeUnit.DAY)
        val isPast = startTime < now
        val isSameYear = targetDateTime.year == currentDateTime.year

        return when {
            isToday -> if (isPast && isLive == false) "Today" else "Today • $hour:$minute"
            isYesterday -> "Yesterday"
            isTomorrow -> "Tomorrow • $hour:$minute"
            else -> {
                val dateStr = if (isSameYear) "$weekday, $dayOfMonth $month" else "$dayOfMonth $month $year"
                if (isPast) dateStr else "$dateStr • $hour:$minute"
            }
        }
    }

    /**
     * Converts a timestamp to a formatted date-time string "dd.MM.yyyy • HH:mm".
     *
     * @param timestamp The timestamp in epoch seconds.
     * @return The formatted date-time string.
     */
    fun formatTimestampToDateTime(timestamp: Long): String {
        val dateTime = timestamp.toLocalDateTime()
        val day = dateTime.dayOfMonth.toDoubleDigitString()
        val month = dateTime.monthNumber.toDoubleDigitString()
        val year = dateTime.year.toString()
        val hour = dateTime.hour.toDoubleDigitString()
        val minute = dateTime.minute.toDoubleDigitString()
        return "$day.$month.$year • $hour:$minute"
    }

    // Format timestamp to a human-readable date and time string
    fun formatTimestampToDateTime(startTime: Long?, timeZone: TimeZone = TimeZone.currentSystemDefault()): String? {
        if (startTime == null) return null
        val localDateTime = Instant.fromEpochSeconds(startTime).toLocalDateTime(timeZone)
        val hour = localDateTime.hour.toDoubleDigitString()
        val minute = localDateTime.minute.toDoubleDigitString()
        val dayOfMonth = localDateTime.dayOfMonth.toDoubleDigitString()
        val month = localDateTime.month.name.take(3)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val year = localDateTime.year
        return "$month $dayOfMonth, $year • $hour:$minute"
    }

    // Convert an Instant to a human-readable local date and time
    fun convertInstantToHumanTime(instant: Instant, timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
        val localDateTime = instant.toLocalDateTime(timeZone)
        val (hour, minute, second, millisecond) = extractFormattedTimeComponents(localDateTime)
        val month = localDateTime.monthNumber
        val day = localDateTime.dayOfMonth
        val year = localDateTime.year
        return "$day/$month/$year $hour:$minute:$second.$millisecond"
    }

    // Extract formatted time components from LocalDateTime
    fun extractFormattedTimeComponents(localDateTime: LocalDateTime): List<String> {
        val hour = localDateTime.hour.toDoubleDigitString()
        val minute = localDateTime.minute.toDoubleDigitString()
        val second = localDateTime.second.toDoubleDigitString()
        val millisecond = (localDateTime.nanosecond / 1_000_000).toString().padStart(3, '0')
        return listOf(hour, minute, second, millisecond)
    }

    // Format the difference in time (Duration) to a readable string
    fun formatDurationToReadableString(duration: Duration): String {
        val sign = if (duration.isNegative()) "-" else "+"
        val totalSeconds = duration.inWholeSeconds
        val hours = (totalSeconds / 3600).toDoubleDigitString()
        val minutes = ((totalSeconds % 3600) / 60).toDoubleDigitString()
        val seconds = (totalSeconds % 60).toDoubleDigitString()
        val milliseconds = ((duration.inWholeNanoseconds % 1_000_000_000) / 1_000_000).toString().padStart(1, '0')
        return "$sign$hours:$minutes:$seconds.$milliseconds"
    }

    // Format timestamp in milliseconds to human-readable time
    fun formatMillisecondsToTime(timestampMillis: Long?, timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
        if (timestampMillis == null || timestampMillis < 0) return "00:00:00.000"
        val dateTime = Instant.fromEpochMilliseconds(timestampMillis).toLocalDateTime(timeZone)
        val hours = dateTime.hour.toDoubleDigitString()
        val minutes = dateTime.minute.toDoubleDigitString()
        val seconds = dateTime.second.toDoubleDigitString()
        val milliseconds = (dateTime.nanosecond / 1_000_000).toString().padStart(3, '0')
        return "$hours:$minutes:$seconds.$milliseconds"
    }
}