package org.nanking.km_flow1000_admin

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "km_flow1000_admin",
    ) {
        App()
    }
}