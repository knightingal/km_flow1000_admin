package org.nanking.km_flow1000_admin

import android.os.Build
import android.util.Log

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getLogger(name: String): Logger = AndroidLogger(name)
actual fun getLogger(clazz: Class<*>): Logger = AndroidLogger(clazz.simpleName)

class AndroidLogger(val tag: String) : Logger {

    override fun d(message: () -> String) {
        Log.d(tag, message())
    }

    override fun w(message: () -> String) {
        Log.w(tag, message())
    }

    override fun i(message: () -> String) {
        Log.i(tag, message())
    }

    override fun e(message: () -> String) {
        Log.e(tag, message())
    }

    override fun e(message: () -> String, error: Throwable) {
        Log.e(tag, message(), error)
    }

}