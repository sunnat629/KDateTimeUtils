package dev.sunnat629.kDateTimeX

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Extension functions related to time and date.
 */

/**
 * Converts an [Int] to a double-digit [String], padding with zero if necessary.
 */
fun Int.toDoubleDigitString(): String = this.toString().padStart(2, '0')

/**
 * Converts an [Int] to a triple-digit [String], padding with zeros if necessary.
 */
fun Int.toTripleDigitString(): String = this.toString().padStart(3, '0')


/**
 * Converts an [Long] to a double-digit [String], padding with zero if necessary.
 */
fun Long.toDoubleDigitString(): String = this.toString().padStart(2, '0')

/**
 * Converts an [Long] to a triple-digit [String], padding with zeros if necessary.
 */
fun Long.toTripleDigitString(): String = this.toString().padStart(3, '0')

/**
 * Converts epoch seconds to [Instant].
 */
fun Long.toInstant(): Instant = Instant.fromEpochSeconds(this)

/**
 * Converts epoch seconds to [LocalDateTime] in the specified [timeZone].
 *
 * @param timeZone The [TimeZone] to use. Defaults to the system's time zone.
 */
fun Long.toLocalDateTime(timeZone: TimeZone = TimeProvider.utcTimeZone): LocalDateTime =
 this.toInstant().toLocalDateTime(timeZone)

/**
 * Converts an ISO 8601 timestamp string to epoch seconds.
 *
 * @throws IllegalArgumentException if the string cannot be parsed as an [Instant].
 */
fun String.toEpochSeconds(): Long = Instant.parse(this).epochSeconds

/**
 * Computes the floor modulus of `x` and `y`.
 *
 * @param x The dividend.
 * @param y The divisor.
 * @return The result of the floor modulus operation.
 */
fun floorMod(x: Int, y: Int): Int {
    val mod = x % y
    return if (mod < 0) mod + y else mod
}