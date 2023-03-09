package org.c2h4.privacy

import com.blankj.utilcode.util.LogUtils

/**
 * Created by jinbangyu on 2022/6/8.
 */
internal object LogUtils {
    private var debugable: Boolean = false

    fun setDebug(debug: Boolean) {
        debugable = debug
    }

    fun dTag(tag: String, vararg contents: Any) {
        if (debugable) {
            LogUtils.dTag(tag, contents, Throwable().stackTrace.joinToString("\n"))
        }
    }
}