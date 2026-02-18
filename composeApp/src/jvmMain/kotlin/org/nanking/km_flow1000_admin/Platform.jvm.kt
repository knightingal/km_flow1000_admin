package org.nanking.km_flow1000_admin

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