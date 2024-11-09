package dev.sunnat629.kDateTimeX

import kotlinx.datetime.*

/**
 * Provides functions to get the current time.
 */
object TimeProvider {

 /**
  * The system's default [TimeZone].
  */
 val systemTimeZone: TimeZone by lazy { TimeZone.currentSystemDefault() }
 val utcTimeZone: TimeZone by lazy { TimeZone.UTC }

 /**
  * Returns the current [Instant] in time.
  */
 fun nowInstant(): Instant = Clock.System.now()

 /**
  * Returns the current epoch seconds.
  */
 fun nowEpochSeconds(): Long = nowInstant().epochSeconds

 /**
  * Returns the current [LocalDateTime] in the system's time zone.
  */
 fun nowLocalDateTime(timeZone: TimeZone = systemTimeZone): LocalDateTime = nowInstant().toLocalDateTime(timeZone)
}
