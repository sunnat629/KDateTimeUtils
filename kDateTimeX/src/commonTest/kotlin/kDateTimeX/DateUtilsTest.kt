import dev.sunnat629.kDateTimeX.DateUtils
import kotlinx.datetime.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DateUtilsTest {

    @Test
    fun testDaysBetween() {
        val startDate = LocalDate(2022, 1, 1)
        val endDate = LocalDate(2022, 1, 31)
        assertEquals(30, DateUtils.daysBetween(startDate, endDate))
    }

    @Test
    fun testAddDaysToTimestamp() {
        val timeZone = TimeZone.UTC
        val timestamp = LocalDate(2022, 1, 1).atStartOfDayIn(timeZone).epochSeconds
        val newTimestamp = DateUtils.addDaysToTimestamp(timestamp, 10)
        val expectedTimestamp = LocalDate(2022, 1, 11).atStartOfDayIn(timeZone).epochSeconds
        assertEquals(expectedTimestamp, newTimestamp)
    }

    @Test
    fun testIsLeapYear() {
        assertTrue(DateUtils.isLeapYear(2020))
        assertFalse(DateUtils.isLeapYear(2019))
        assertFalse(DateUtils.isLeapYear(1900))
        assertTrue(DateUtils.isLeapYear(2000))
    }

    @Test
    fun testGetStartOfDay() {
        val timeZone = TimeZone.UTC
        val timestamp = LocalDate(2022, 1, 15)
            .atTime(12, 34, 56)
            .toInstant(timeZone)
            .epochSeconds

        val startOfDayTimestamp = DateUtils.getStartOfDay(timestamp)
        val expectedTimestamp = LocalDate(2022, 1, 15).atStartOfDayIn(timeZone).epochSeconds

        assertEquals(expectedTimestamp, startOfDayTimestamp)
    }

    @Test
    fun testGetEndOfDay() {
        val timeZone = TimeZone.UTC
        val timestamp = LocalDate(2022, 1, 15)
            .atTime(12, 34, 56)
            .toInstant(timeZone)
            .epochSeconds

        val endOfDayTimestamp = DateUtils.getEndOfDay(timestamp)
        val endOfDay = LocalDate(2022, 1, 15)
            .plus(1, DateTimeUnit.DAY)
            .atStartOfDayIn(timeZone)
            .minus(1, DateTimeUnit.SECOND)
            .epochSeconds

        assertEquals(endOfDay, endOfDayTimestamp)
    }
}