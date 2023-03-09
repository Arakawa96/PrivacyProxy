package org.c2h4.plugin.privacy.utils

/**
 * Created by JBY on 2023/2/23.
 */
object Log {
    @JvmStatic
    fun info(msg: Any) {
        try {
            println((String.format("{%s}", msg.toString())))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}