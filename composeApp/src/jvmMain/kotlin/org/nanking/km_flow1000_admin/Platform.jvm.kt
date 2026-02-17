package org.nanking.km_flow1000_admin

import org.slf4j.LoggerFactory

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun getLogger(): Logger = JVMLogger()

class JVMLogger: Logger {

    override fun d(tag: String, message: () -> String) {
        val logger = LoggerFactory.getLogger(tag)
        logger.debug(message())
    }

    override fun w(tag: String, message: () -> String) {
        val logger = LoggerFactory.getLogger(tag)
        logger.warn(message())
    }

    override fun i(tag: String, message: () -> String) {
        val logger = LoggerFactory.getLogger(tag)
        logger.info(message())
    }

    override fun e(tag: String, message: () -> String) {
        val logger = LoggerFactory.getLogger(tag)
        logger.error(message())
    }

    override fun e(tag: String, message: () -> String, error: Throwable) {
        val logger = LoggerFactory.getLogger(tag)
        logger.error(message(), error)
    }

}