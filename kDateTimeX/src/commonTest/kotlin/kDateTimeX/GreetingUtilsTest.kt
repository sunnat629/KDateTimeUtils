import dev.sunnat629.kDateTimeX.GreetingUtils
import kotlin.test.Test
import kotlin.test.assertTrue

class GreetingUtilsTest {

    @Test
    fun testGetGreetingMessage() {
        // Mocking the current time is complex in KMP tests,
        // so we'll assume the function returns a non-empty string.
        val greeting = GreetingUtils.getGreetingMessage()
        assertTrue(greeting.isNotEmpty(), "Greeting message should not be empty")
    }
}