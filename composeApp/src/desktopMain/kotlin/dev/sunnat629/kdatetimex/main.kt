package dev.sunnat629.kdatetimex

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.sunnat629.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KDateTimeX",
    ) {
        App()
    }
}