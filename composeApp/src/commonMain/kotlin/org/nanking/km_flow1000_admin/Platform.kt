package org.nanking.km_flow1000_admin

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