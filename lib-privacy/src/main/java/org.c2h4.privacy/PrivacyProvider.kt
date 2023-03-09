package org.c2h4.privacy

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.ContentResolver
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationManager
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import android.provider.Settings
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import androidx.annotation.Keep
import com.blankj.utilcode.util.Utils
import com.tencent.mmkv.MMKV
import java.net.NetworkInterface

/**
 * Created by jinbangyu on 2022/5/19.
 */
@Keep
object PrivacyProvider {
    const val TAG = "PrivacyProvider"
    private val memoryStringCache = HashMap<String, String?>()

    //    lateinit var context: Context
    private val mmkv by lazy {
        MMKV.initialize(Utils.getApp().applicationContext)
        MMKV.mmkvWithID(TAG)
    }

    @Synchronized
    private fun getSafeDeviceId(): String {
        val deviceId = mmkv.decodeString("deviceId", null)
        if (deviceId != null) {
            return deviceId
        } else {
            return ""
        }
    }

    /**
     * 在合适的时机调用，以获取android_id
     */
    fun trackAndroidId() {
        val deviceId = mmkv.decodeString("deviceId", null)
        if (deviceId == null) {
            val androidId = Settings.System.getString(
                Utils.getApp().contentResolver,
                Settings.Secure.ANDROID_ID
            )
            mmkv.encode("deviceId", androidId)
        }
    }


    @SuppressLint("MissingPermission", "HardwareIds")
    @JvmStatic
    fun getDeviceId(manager: TelephonyManager): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        LogUtils.dTag(TAG, "调用getDeviceId()获取了imei")
        return getSafeDeviceId()
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    @JvmStatic
    fun getDeviceId(manager: TelephonyManager, slotIndex: Int): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        LogUtils.dTag(TAG, "调用getDeviceId(int)获取了imei")
        return getSafeDeviceId()
    }

    @SuppressLint("MissingPermission")
    @JvmStatic
    fun getImei(manager: TelephonyManager): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        LogUtils.dTag(TAG, "调用getImei()获取了imei")
        return getSafeDeviceId()
    }

    @SuppressLint("MissingPermission")
    @JvmStatic
    fun getImei(manager: TelephonyManager, slotIndex: Int): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        LogUtils.dTag(TAG, "调用getImei(int)获取了imei")
        return getSafeDeviceId()
    }

    @JvmStatic
    fun getSubscriberId(manager: TelephonyManager): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        LogUtils.dTag(TAG, "调用getSubscriberId()获取了imsi")
        return getSafeDeviceId()
    }

    @SuppressLint("MissingPermission")
    @JvmStatic
    fun getMacAddress(wifiInfo: WifiInfo): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        LogUtils.dTag(TAG, "调用getMacAddress()获取了mac")
        val macAddress = memoryStringCache["macAddress"]
        if (macAddress != null) {
            return macAddress
        } else {
            val mac = wifiInfo.macAddress
            memoryStringCache["macAddress"] = mac
            return mac
        }
    }

    // NetworkInterface getHardwareAddress()
    @JvmStatic
    fun getHardwareAddress(networkInterface: NetworkInterface): ByteArray? {
        if (PrivacyManager.isGuideMode()) {
            return ByteArray(0)
        }
        LogUtils.dTag(TAG, "调用getHardwareAddress()获取了mac")
        return null
    }

    @JvmStatic
    fun getSettingsSecuregetstring(resolver: ContentResolver, name: String): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        LogUtils.dTag(TAG, "调用getSettingsSecuregetstring()获取了$name")
        if (name == "android_id") {
            return getSafeDeviceId()
        } else {
            val value = mmkv.decodeString("Secure:$name", null)
            if (value != null) {
                return value
            } else {
                val secure = Settings.Secure.getString(resolver, name)
                mmkv.encode("Secure:$name", secure)
                return secure
            }
        }
    }

    @JvmStatic
    fun getSettingsSystemgetstring(resolver: ContentResolver, name: String): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        LogUtils.dTag(TAG, "调用getSettingsSystemgetstring()获取了$name")
        if (name == "android_id") {
            return getSafeDeviceId()
        } else {
            val value = mmkv.decodeString("System:$name", null)
            if (value != null) {
                return value
            } else {
                val secure = Settings.System.getString(resolver, name)
                mmkv.encode("System:$name", secure)
                return secure
            }
        }
    }

    @SuppressLint("MissingPermission")
    @JvmStatic
    fun getLastKnownLocation(locationManager: LocationManager, provider: String): Location? {
        if (PrivacyManager.isGuideMode()) {
            return null
        }
        LogUtils.dTag(TAG, "调用getLastKnownLocation by provider: ${provider}")
        val location =
            mmkv.decodeParcelable<Location>("lastKnownLocation:$provider", Location::class.java)
        if (location != null) {
            return location
        } else {
            val lastKnownLocation = locationManager.getLastKnownLocation(provider)
            mmkv.encode("lastKnownLocation:$provider", lastKnownLocation)
            return lastKnownLocation
        }
    }

    @JvmStatic
    fun getInstalledPackages(packageManager: PackageManager, flags: Int): MutableList<PackageInfo> {
        val stack = Throwable().stackTrace
        val methodName = stack[1].toString()
        LogUtils.dTag(TAG, "${methodName}:调用getInstalledApplications获取了安装列表")
        val isBlack = stack.joinToString("\n").contains("tendcloud")
        if (isBlack) return mutableListOf()
        LogUtils.dTag(TAG, "getInstalledPackages")
        return mutableListOf()
    }

    @JvmStatic
    fun getInstalledApplications(
        packageManager: PackageManager,
        flags: Int
    ): MutableList<ApplicationInfo> {
        val stack = Throwable().stackTrace
        val methodName = stack[1].toString()
        LogUtils.dTag(TAG, "${methodName}:调用getInstalledApplications获取了安装列表")
        val isBlack = stack.joinToString("\n").contains("tendcloud")
        if (isBlack) return mutableListOf()
        LogUtils.dTag(TAG, "getInstalledApplications")
        return mutableListOf()
    }

    @JvmStatic
    fun getImeiNew(context: Context): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        LogUtils.dTag(TAG, "调用getImeiNew()获取了imei")
        return getSafeDeviceId()
    }

    @JvmStatic
    fun getSecondSimIMEi(context: Context): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        LogUtils.dTag(TAG, "调用getSecondSimIMEi()获取了imei")
        return getSafeDeviceId()
    }

    @JvmStatic
    fun getRunningAppProcesses(activityManager: ActivityManager): List<ActivityManager.RunningAppProcessInfo> {
        return LastUseDispatcher.dispatch("getRunningAppProcesses", 60 * 1000) {
            return@dispatch emptyList()
        }
    }

    @JvmStatic
    fun getNetWorkInfoIsAvailable(networkInfo: NetworkInfo): Boolean {
        return LastUseDispatcher.dispatch("getNetWorkInfoIsAvailable", 60 * 1000) {
            return@dispatch networkInfo.isAvailable
        }
    }

    @JvmStatic
    fun getNetWorkInfoIsConnected(networkInfo: NetworkInfo): Boolean {
        return LastUseDispatcher.dispatch("getNetWorkInfoIsConnected", 60 * 1000) {
            return@dispatch networkInfo.isConnected
        }
    }

    @JvmStatic
    fun getNetWorkInfoGetType(networkInfo: NetworkInfo): Int {
        return LastUseDispatcher.dispatch("getNetWorkInfoGetType", 60 * 1000) {
            return@dispatch networkInfo.type
        }
    }

    // ICCID
    @JvmStatic
    fun getSimSerialNumber(telephonyManager: TelephonyManager): String {
        if (PrivacyManager.isGuideMode()) {
            return ""
        }
        return getSafeDeviceId()
    }

    // ICCID
    @JvmStatic
    fun getActiveSubscriptionInfoList(sm: SubscriptionManager): List<SubscriptionInfo> {
        if (PrivacyManager.isGuideMode()) {
            return emptyList()
        }
        return emptyList()
    }

    //getPackageInfo
    @JvmStatic
    @Throws(PackageManager.NameNotFoundException::class)
    fun getPackageInfo(
        packageManager: PackageManager,
        packageName: String,
        flags: Int
    ): PackageInfo {
        if (PrivacyConstants.PACKAGE_INFO_WHITE_LIST.contains(packageName)) {
            return LastUseDispatcher.dispatch<PackageInfo?>(
                "getPackageInfo_${packageName}_$flags",
                60 * 1000
            ) {
                LogUtils.dTag(TAG, "调用getPackageInfo查找:${packageName}")
                return@dispatch packageManager.getPackageInfo(packageName, flags)
            } ?: throw PackageManager.NameNotFoundException(packageName)
        } else {
            throw PackageManager.NameNotFoundException(packageName)
        }
    }

    @JvmStatic
    fun getSimState(telephonyManager: TelephonyManager): Int {
        return LastUseDispatcher.dispatch("getSimState", 60 * 1000) {
            LogUtils.dTag(TAG, "调用getSimState获取:${telephonyManager.simState}")
            return@dispatch telephonyManager.simState
        }
    }

    @SuppressLint("NewApi")
    @JvmStatic
    fun getSimState(telephonyManager: TelephonyManager, slotIndex: Int): Int {
        return LastUseDispatcher.dispatch("getSimState(I)", 60 * 1000) {
            LogUtils.dTag(TAG, "调用getSimState(I)获取:${telephonyManager.getSimState(slotIndex)}")
            return@dispatch telephonyManager.getSimState(slotIndex)
        }
    }

    @JvmStatic
    fun getSerialNo(): String {
        return ""
    }

    @JvmStatic
    fun getDefaultSensor(manager: SensorManager, type:Int): Sensor? {
        return null
    }
}