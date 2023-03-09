package org.c2h4.privacy

object PrivacyConstants {

    const val GUIDE_MODE: String = "guide_mode"
    val PACKAGE_INFO_WHITE_LIST by lazy {
        arrayOf(
            "org.c2h4.afei.beauty",
            "com.tencent.mm",
            "com.tencent.tim",
            "com.tencent.mobileqq",
            "com.sina.weibo",
            "com.xingin.xhs",
            "com.taobao.taobao"
        )
    }
}
