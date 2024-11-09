import dev.sunnat629.kDateTimeX.TimeFormatter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

class TimeFormatterTest {

    @Test
    fun testFormatDurationSeconds() {
        assertEquals("00:00:00", TimeFormatter.formatDurationSeconds(0.0))
        assertEquals("00:00:01", TimeFormatter.formatDurationSeconds(1.0))
        assertEquals("00:01:01", TimeFormatter.formatDurationSeconds(61.0))
        assertEquals("01:01:01", TimeFormatter.formatDurationSeconds(3661.0))
    }

    @Test
    fun testFormatMillisecondsToTime() {
        assertEquals("00:00:00.000", TimeFormatter.formatMillisecondsToTime(0L))
        assertEquals("00:00:01.000", TimeFormatter.formatMillisecondsToTime(1000L))
        assertEquals("01:00:00.000", TimeFormatter.formatMillisecondsToTime(3600000L))
    }

    @Test
    fun testFormatMillisecondsToDate() {
        assertEquals("1970-01-01", TimeFormatter.formatMillisecondsToDate(0L))
        assertEquals("2021-12-01", TimeFormatter.formatMillisecondsToDate(1638316800000L))
    }

    @Test
    fun testFormatTimestampToDateTime() {
        val timestamp = 1638316800L // 2021-12-01 00:00:00 UTC
        val formattedDateTime = TimeFormatter.formatTimestampToDateTime(timestamp)
        assertTrue(formattedDateTime.startsWith("01.12.2021 • "), "Date should be formatted correctly")
    }

    @Test
    fun testFormatTimestampToTime() {
        val timestamp = 1731024000L // 00:00:00 UTC
        val timestampWithTime = 1731060000L // 10:00:00 UTC
        println("TimeFormatter.formatTimestampToTime(timestamp) ${TimeFormatter.formatTimestampToTime(timestamp)}")
        println("TimeFormatter.formatTimestampToTime(timestamp) ${TimeFormatter.formatTimestampToTime(timestampWithTime)}")
        assertEquals("00:00", TimeFormatter.formatTimestampToTime(timestamp))
        assertEquals("10:00", TimeFormatter.formatTimestampToTime(timestampWithTime))
    }

    @Test
    fun testFormatTimestampToTimeWithSeconds() {
        val timestamp = 1731024000L // 00:00:00 UTC
        assertEquals("00:00:00", TimeFormatter.formatTimestampToTime(timestamp, includeSeconds = true))
    }

    @Test
    fun testFormatMillisecondsWithLimitedDays() {
        assertEquals("0 00:00:00.000", TimeFormatter.formatMillisecondsWithLimitedDays(0L))
        assertEquals("0 00:00:01.000", TimeFormatter.formatMillisecondsWithLimitedDays(1000L))
        assertEquals("0 00:01:40.000", TimeFormatter.formatMillisecondsWithLimitedDays(100000L))
        assertEquals("1 00:00:00.000", TimeFormatter.formatMillisecondsWithLimitedDays(86400000L))
        assertEquals("X 10:00:00.000", TimeFormatter.formatMillisecondsWithLimitedDays(900000000L)) // Over 9 days
    }

    @Test
    fun testFormatDurationWithSign() {
        val duration = (-3661).seconds
        assertEquals("-01:01:01.0", TimeFormatter.formatDurationWithSign(duration))
        val positiveDuration = 3661.seconds
        assertEquals("+01:01:01.0", TimeFormatter.formatDurationWithSign(positiveDuration))
    }
}