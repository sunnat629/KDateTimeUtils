package dev.sunnat629.kDateTimeX

/**
 * Contains functions related to greetings based on time.
 */
object GreetingUtils {

    /**
     * Returns a greeting message based on the current time.
     *
     * @return The greeting message.
     */
    fun getGreetingMessage(): String {
        val hour = TimeProvider.nowLocalDateTime().hour
        return when (hour) {
            in 0..5 -> "Good Night"
            in 6..11 -> "Good Morning"
            in 12..17 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }
}
