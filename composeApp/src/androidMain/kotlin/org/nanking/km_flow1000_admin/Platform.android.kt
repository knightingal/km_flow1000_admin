package org.nanking.km_flow1000_admin

import android.os.Build
import android.util.Log

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getLogger(): Logger = AndroidLogger()

class AndroidLogger : Logger {
    override fun d(tag: String, message: () -> String) {
        Log.d(tag, message())
    }

    override fun w(tag: String, message: () -> String) {
        Log.w(tag, message())
    }

    override fun i(tag: String, message: () -> String) {
        Log.i(tag, message())
    }

    override fun e(tag: String, message: () -> String) {
        Log.e(tag, message())
    }

    override fun e(tag: String, message: () -> String, error: Throwable) {
        Log.e(tag, message(), error)
    }

}