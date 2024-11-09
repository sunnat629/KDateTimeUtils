import dev.sunnat629.kDateTimeX.TimeFormatter.convertInstantToHumanTime
import dev.sunnat629.kDateTimeX.TimeFormatter.extractFormattedTimeComponents
import dev.sunnat629.kDateTimeX.TimeFormatter.formatDurationToReadableString
import dev.sunnat629.kDateTimeX.TimeFormatter.formatMillisecondsToTime
import dev.sunnat629.kDateTimeX.TimeFormatter.formatTime
import dev.sunnat629.kDateTimeX.TimeFormatter.formatTimestampToDateTime
import dev.sunnat629.kDateTimeX.TimeProvider
import kotlinx.datetime.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration

class TimeProviderTest {

    private val testTimeZone = TimeZone.UTC

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

        @Test
        fun testFormatMillisecondsToTime_validInput() {
            val timestamp = 1731186937000L // 1731186937 seconds in milliseconds
            val result = formatMillisecondsToTime(timestamp)
            assertEquals("22:15:37.000", result)
        }

        @Test
        fun testFormatMillisecondsToTime_nullInput() {
            val result = formatMillisecondsToTime(null)
            assertEquals("00:00:00.000", result)
        }

        @Test
        fun testFormatMillisecondsToTime_negativeInput() {
            val result = formatMillisecondsToTime(-1000L)
            assertEquals("00:00:00.000", result)
        }

        @Test
        fun testFormatTime_isToday() {
            val now = Clock.System.now().epochSeconds
            val result = formatTime(now)
            result?.contains("Today")?.let { assertTrue(it) }
        }

    @Test
    fun testFormatTime_isYesterday() {
        // Fixed reference date for testing
        val now = Clock.System.now().epochSeconds
        val yesterday = Instant.fromEpochSeconds(now).minus(1, DateTimeUnit.DAY, testTimeZone).epochSeconds

        val result = formatTime(yesterday, testTimeZone)
        assertEquals("Yesterday", result)
    }

    @Test
    fun testFormatTime_isTomorrow() {
        // Fixed reference date for testing
        val now = Clock.System.now().epochSeconds
        val tomorrow = Instant.fromEpochSeconds(now).plus(1, DateTimeUnit.DAY, testTimeZone).epochSeconds

        val result = formatTime(tomorrow, testTimeZone)
        assertTrue(result?.contains("Tomorrow") == true)
    }

        @Test
        fun testFormatTimestampToDateTime() {
            val timestamp = 1731186937L
            val result = formatTimestampToDateTime(timestamp)
            println(result)
            assertTrue(result.contains("21:15"))
        }

        @Test
        fun testConvertInstantToHumanTime() {
            val instant = Instant.fromEpochSeconds(1731186937L)
            val result = convertInstantToHumanTime(instant)
            assertEquals("9/11/2024 22:15:37.000", result)
        }

        @Test
        fun testFormatDurationToReadableString_positiveDuration() {
            val duration = Duration.parse("PT3H25M45S")
            val result = formatDurationToReadableString(duration)
            assertEquals("+03:25:45.0", result)
        }

//        @Test
//        fun testFormatDurationToReadableString_negativeDuration() {
//            val duration = Duration.parse("-PT1H15M30S")
//            val result = formatDurationToReadableString(duration)
//            assertEquals("-01:15:30.0", result)
//        }

        @Test
        fun testExtractFormattedTimeComponents() {
            val dateTime = LocalDateTime(2024, 11, 9, 12, 34, 56, 789_000_000)
            val components = extractFormattedTimeComponents(dateTime)
            assertEquals(listOf("12", "34", "56", "789"), components)
    }
}