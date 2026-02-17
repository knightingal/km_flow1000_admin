package org.nanking.km_flow1000_admin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getLogger(): Logger

interface Logger {
    fun d(tag: String, message: () -> String)
    fun w(tag: String, message: () -> String)
    fun i(tag: String, message: () -> String)
    fun e(tag: String, message: () -> String)
    fun e(tag: String, message: () -> String, error: Throwable)
}