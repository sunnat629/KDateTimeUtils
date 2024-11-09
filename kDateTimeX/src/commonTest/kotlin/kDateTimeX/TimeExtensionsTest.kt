import dev.sunnat629.kDateTimeX.*
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TimeExtensionsTest {

    @Test
    fun testToDoubleDigitString() {
        assertEquals("05", 5.toDoubleDigitString())
        assertEquals("15", 15.toDoubleDigitString())
        assertEquals("00", 0.toDoubleDigitString())
        assertEquals("99", 99.toDoubleDigitString())
    }

    @Test
    fun testToTripleDigitString() {
        assertEquals("005", 5.toTripleDigitString())
        assertEquals("015", 15.toTripleDigitString())
        assertEquals("000", 0.toTripleDigitString())
        assertEquals("999", 999.toTripleDigitString())
    }

    @Test
    fun testLongToInstant() {
        val epochSeconds = 1638307200L // Corresponds to 2021-12-01T00:00:00Z
        val instant = epochSeconds.toInstant()
        assertEquals(epochSeconds, instant.epochSeconds)
    }

    @Test
    fun testLongToLocalDateTime() {
        val epochSeconds = 1731024000L // Corresponds to 2024-11-08T00:00:00Z
        val localDateTime = epochSeconds.toLocalDateTime(TimeZone.UTC)
        assertEquals(2024, localDateTime.year)
        assertEquals(11, localDateTime.monthNumber)
        assertEquals(8, localDateTime.dayOfMonth)
    }

    @Test
    fun testStringToEpochSeconds() {
        val isoString = "2024-11-08T00:00:00Z"
        val epochSeconds = isoString.toEpochSeconds()
        assertEquals(1731024000L, epochSeconds)
    }

    @Test
    fun testStringToEpochSecondsInvalid() {
        val invalidIsoString = "invalid-date-time"
        assertFailsWith<IllegalArgumentException> {
            invalidIsoString.toEpochSeconds()
        }
    }
}