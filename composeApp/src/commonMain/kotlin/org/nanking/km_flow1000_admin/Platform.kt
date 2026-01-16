package org.nanking.km_flow1000_admin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform