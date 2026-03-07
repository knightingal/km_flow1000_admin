package org.nanking.km_flow1000_admin

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.v2.ScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.slf4j.LoggerFactory

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun getLogger(name: String): Logger = JVMLogger(name)
actual fun getLogger(clazz: Class<*>): Logger = JVMLogger(clazz.simpleName)

class JVMLogger(name: String): Logger {
    val logger = LoggerFactory.getLogger(name)

    override fun d(message: () -> String) {
        if (logger.isDebugEnabled) {
            logger.debug(message())
        }
    }

    override fun w(message: () -> String) {
        if (logger.isWarnEnabled) {
            logger.warn(message())
        }
    }

    override fun i(message: () -> String) {
        if (logger.isInfoEnabled) {
            logger.info(message())
        }
    }

    override fun e(message: () -> String) {
        logger.error(message())
    }

    override fun e(message: () -> String, error: Throwable) {
        logger.error(message(), error)
    }

}

@Composable
actual fun PlatformVerticalScrollbar(modifier: Modifier, lazyStaggeredGridState: LazyStaggeredGridState) {

    VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(
            lazyStaggeredGridState = lazyStaggeredGridState
        )
    )
}

@Composable
fun rememberScrollbarAdapter(
    lazyStaggeredGridState: LazyStaggeredGridState,
): ScrollbarAdapter = remember(lazyStaggeredGridState) {
    ScrollbarAdapter(lazyStaggeredGridState)
}


fun ScrollbarAdapter(
    scrollState: LazyStaggeredGridState
): ScrollbarAdapter = object : ScrollbarAdapter {
    override val scrollOffset: Double
        get() = scrollState.scrollIndicatorState!!.scrollOffset.toDouble()
    override val contentSize: Double
        get() = scrollState.scrollIndicatorState!!.contentSize.toDouble()
    override val viewportSize: Double
        get() = scrollState.scrollIndicatorState!!.viewportSize.toDouble()

    override suspend fun scrollTo(scrollOffset: Double) {
        val targetOffset = scrollOffset - this.scrollOffset
        scrollState.customScroll {
            scrollBy(targetOffset.toFloat())
        }
    }
}
