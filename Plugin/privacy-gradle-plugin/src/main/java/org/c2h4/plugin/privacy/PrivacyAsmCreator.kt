package org.c2h4.plugin.privacy

import org.objectweb.asm.Opcodes

//top.arakawa.hacg.PrivacyProvider -> top/arakawa/hacg/PrivacyProvider
// todo 当前版本先手动修改，后续通过注解进行动态添加配置
object PrivacyCreator {

    fun MutableList<Pair<PrivacyAsmEntity, PrivacyAsmEntity>>.addItem(
        srcCode: Int,
        srcOwner: String,
        srcName: String,
        srcDesc: String,
        dscCode: Int,
        dscOwner: String,
        dscName: String,
        dscDesc: String,
    ) {
        this.add(
            Pair(
                PrivacyAsmEntity(
                    srcCode,
                    srcOwner,
                    srcName,
                    srcDesc
                ),
                PrivacyAsmEntity(
                    dscCode,
                    dscOwner,
                    dscName,
                    dscDesc
                )
            )
        )
    }

    val privacyList = mutableListOf<Pair<PrivacyAsmEntity, PrivacyAsmEntity>>().apply {

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/telephony/TelephonyManager",
            "getDeviceId",
            "()Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getDeviceId",
            "(Landroid/telephony/TelephonyManager;)Ljava/lang/String;"
        )
        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/telephony/TelephonyManager",
            "getDeviceId",
            "(I)Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getDeviceId",
            "(Landroid/telephony/TelephonyManager;I)Ljava/lang/String;"
        )
        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/telephony/TelephonyManager",
            "getImei",
            "()Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getImei",
            "(Landroid/telephony/TelephonyManager;)Ljava/lang/String;"
        )
        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/telephony/TelephonyManager",
            "getImei",
            "(I)Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getImei",
            "(Landroid/telephony/TelephonyManager;I)Ljava/lang/String;"
        )
        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/telephony/TelephonyManager",
            "getSubscriberId",
            "()Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getSubscriberId",
            "(Landroid/telephony/TelephonyManager;)Ljava/lang/String;"
        )
        //WifiInfo getMacAddress()
        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/net/wifi/WifiInfo",
            "getMacAddress",
            "()Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getMacAddress",
            "(Landroid/net/wifi/WifiInfo;)Ljava/lang/String;"
        )
        // NetworkInterface getHardwareAddress()
        addItem(
            Opcodes.INVOKEVIRTUAL,
            "java/net/NetworkInterface",
            "getHardwareAddress",
            "()[B",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getHardwareAddress",
            "(Ljava/net/NetworkInterface;)[B"
        )
        //Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        addItem(
            Opcodes.INVOKESTATIC,
            "android/provider/Settings\$Secure",
            "getString",
            "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getSettingsSecuregetstring",
            "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;"
        )
        addItem(
            Opcodes.INVOKESTATIC,
            "android/provider/Settings\$System",
            "getString",
            "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getSettingsSystemgetstring",
            "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;"
        )
//        getLastKnownLocation(LocationManager.GPS_PROVIDER)
        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/location/LocationManager",
            "getLastKnownLocation",
            "(Ljava/lang/String;)Landroid/location/Location;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getLastKnownLocation",
            "(Landroid/location/LocationManager;Ljava/lang/String;)Landroid/location/Location;"
        )

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/content/pm/PackageManager",
            "getInstalledPackages",
            "(I)Ljava/util/List;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getInstalledPackages",
            "(Landroid/content/pm/PackageManager;I)Ljava/util/List;"
        )

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/content/pm/PackageManager",
            "getInstalledApplications",
            "(I)Ljava/util/List;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getInstalledApplications",
            "(Landroid/content/pm/PackageManager;I)Ljava/util/List;"
        )

        //umeng getImeiNew com.umeng.commonsdk.statistics.common;
        addItem(
            Opcodes.INVOKESTATIC,
            "com/umeng/commonsdk/statistics/common/DeviceConfig",
            "getImeiNew",
            "(Landroid/content/Context;)Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getImeiNew",
            "(Landroid/content/Context;)Ljava/lang/String;"
        )

        addItem(
            Opcodes.INVOKESTATIC,
            "com/umeng/commonsdk/statistics/common/DeviceConfig",
            "getSecondSimIMEi",
            "(Landroid/content/Context;)Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getSecondSimIMEi",
            "(Landroid/content/Context;)Ljava/lang/String;"
        )

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/app/ActivityManager",
            "getRunningAppProcesses",
            "()Ljava/util/List;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getRunningAppProcesses",
            "(Landroid/app/ActivityManager;)Ljava/util/List;"
        )

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/net/NetworkInfo",
            "isAvailable",
            "()Z",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getNetWorkInfoIsAvailable",
            "(Landroid/net/NetworkInfo;)Z"
        )
        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/net/NetworkInfo",
            "isConnected",
            "()Z",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getNetWorkInfoIsConnected",
            "(Landroid/net/NetworkInfo;)Z"
        )
        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/net/NetworkInfo",
            "getType",
            "()I",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getNetWorkInfoGetType",
            "(Landroid/net/NetworkInfo;)I"
        )

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/telephony/TelephonyManager",
            "getSimSerialNumber",
            "()Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getSimSerialNumber",
            "(Landroid/telephony/TelephonyManager;)Ljava/lang/String;"
        )

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/telephony/SubscriptionManager",
            "getActiveSubscriptionInfoList",
            "()Ljava/util/List;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getActiveSubscriptionInfoList",
            "(Landroid/telephony/SubscriptionManager;)Ljava/util/List;"
        )

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/content/pm/PackageManager",
            "getPackageInfo",
            "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getPackageInfo",
            "(Landroid/content/pm/PackageManager;Ljava/lang/String;I)Landroid/content/pm/PackageInfo;"
        )

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/telephony/TelephonyManager",
            "getSimState",
            "()I",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getSimState",
            "(Landroid/telephony/TelephonyManager;)I"
        )

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/telephony/TelephonyManager",
            "getSimState",
            "(I)I",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getSimState",
            "(Landroid/telephony/TelephonyManager;I)I"
        )

        addItem(
            Opcodes.INVOKESTATIC,
            "com/umeng/commonsdk/statistics/common/DeviceConfig",
            "getSerialNo",
            "()Ljava/lang/String;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getSerialNo",
            "()Ljava/lang/String;"
        )

        addItem(
            Opcodes.INVOKEVIRTUAL,
            "android/hardware/SensorManager",
            "getDefaultSensor",
            "(I)Landroid/hardware/Sensor;",
            Opcodes.INVOKESTATIC,
            "org/c2h4/privacy/PrivacyProvider",
            "getDefaultSensor",
            "(Landroid/hardware/SensorManager;I)Landroid/hardware/Sensor;"
        )

    }

    //不要被Hook的路径、类名
    val whiteList =
        mutableListOf(
            "org/c2h4/privacy/PrivacyProvider",
            "org/c2h4/privacy/proxy",
            "com/bumptech/glide"
        )
}