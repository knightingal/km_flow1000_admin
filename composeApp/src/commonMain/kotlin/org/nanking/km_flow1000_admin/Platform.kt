package org.nanking.km_flow1000_admin

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getLogger(clazz: Class<*>): Logger
expect fun getLogger(name: String): Logger

interface Logger {
    fun d(message: () -> String)
    fun w(message: () -> String)
    fun i(message: () -> String)
    fun e(message: () -> String)
    fun e(message: () -> String, error: Throwable)
}

@Composable
expect fun PlatformVerticalScrollbar(modifier: Modifier, lazyStaggeredGridState: LazyStaggeredGridState): Unit

@Composable
expect fun PlatformVerticalScrollbar(modifier: Modifier, scrollState: LazyListState): Unit