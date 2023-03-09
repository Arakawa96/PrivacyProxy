package org.c2h4.privacy

/**
 * Created by jinbangyu on 2022/6/7.
 */
object LastUseDispatcher {
    private val lastUseMap = mutableMapOf<String, Long>()
    private val cache = HashMap<String, Any?>()

    @Synchronized
    fun <T : Any?> dispatch(key: String,delay: Long, newValue: () -> T): T {
        val lastUse = lastUseMap[key]
        val now = System.currentTimeMillis()
        lastUseMap[key] = System.currentTimeMillis()
        if (lastUse == null || now - lastUse > delay) {
            return newValue().apply { cache[key] = this }
        } else {
            return cache[key] as T
        }
    }
}