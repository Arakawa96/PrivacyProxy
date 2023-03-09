package org.c2h4.privacy

import com.blankj.utilcode.util.Utils
import com.tencent.mmkv.MMKV

/**
 * Created by jinbangyu on 2022/5/21.
 */
object PrivacyManager {
    const val TAG = "PrivacyManager"

    private var debugable: Boolean = false

    fun setDebug(debug: Boolean) {
        debugable = debug
        LogUtils.setDebug(debug)
    }

    private val mmkv by lazy {
        MMKV.initialize(Utils.getApp().applicationContext)
        MMKV.mmkvWithID(TAG)
    }

    fun isGuideMode(): Boolean {
        return mmkv.decodeBool(PrivacyConstants.GUIDE_MODE, true)
    }

    fun setGuideMode(isGuide: Boolean) {
        mmkv.encode(PrivacyConstants.GUIDE_MODE, isGuide)
    }

    /**
     * 在合适的时机调用，以便记录下追踪ID
     */
    fun safeTrack() {
        PrivacyProvider.trackAndroidId()
    }

}