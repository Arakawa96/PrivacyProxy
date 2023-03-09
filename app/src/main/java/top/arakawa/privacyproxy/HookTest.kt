package top.arakawa.privacyproxy

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import android.net.NetworkInfo
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import org.c2h4.privacy.PrivacyManager
import org.c2h4.privacy.PrivacyProvider

/**
 * Created by jinbangyu on 2022/6/7.
 */
fun getNetWorkInfoIsAvailable(networkInfo: NetworkInfo): Boolean {
    networkInfo.isConnected
    networkInfo.type
    return networkInfo.isAvailable
}

fun getRunningAppProcesses(activityManager: ActivityManager): List<ActivityManager.RunningAppProcessInfo> {
    return activityManager.runningAppProcesses
}

// ICCID
@SuppressLint("MissingPermission")
fun getSimSerialNumber(telephonyManager: TelephonyManager): String {
    if (PrivacyManager.isGuideMode()) {
        return ""
    }
    return telephonyManager.simSerialNumber
}

// ICCID
@SuppressLint("MissingPermission", "NewApi")
fun getActiveSubscriptionInfoList(sm: SubscriptionManager): List<SubscriptionInfo> {
    if (PrivacyManager.isGuideMode()) {
        return emptyList()
    }
    return sm.getActiveSubscriptionInfoList()
}

//getPackageInfo
@Throws(PackageManager.NameNotFoundException::class)
fun getPackageInfo(packageManager: PackageManager, packageName: String, flags: Int): PackageInfo? {
    return packageManager.getPackageInfo(packageName,flags)
}

fun getSystemService(context: Context, name: String): Any {
    val stack = Throwable().stackTrace
    val methodName = stack[1].toString()
    return context.getSystemService(name)
}

fun getSimState(telephonyManager: TelephonyManager): Int {
    return telephonyManager.simState
}

@SuppressLint("NewApi")
fun getSimState(telephonyManager: TelephonyManager,slotIndex:Int): Int {
    return telephonyManager.getSimState(slotIndex)
}

fun getDefaultSensor(manager:SensorManager?): Sensor? {
//    return PrivacyProvider.getDefaultSensor(manager,0)
    return manager?.getDefaultSensor(0)
}