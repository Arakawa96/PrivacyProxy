package org.c2h4.privacy.proxy

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.*
import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.UserHandle
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.LogUtils

/**
 * Created by jinbangyu on 2022/5/24.
 */
class PackageManageImpl(val packageManager: PackageManager, val isBlack: Boolean) :
    PackageManager() {
    private val TAG = "PackageManageImpl"
    override fun getPackageInfo(packageName: String, flags: Int): PackageInfo {
        return packageManager.getPackageInfo(packageName, flags)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getPackageInfo(versionedPackage: VersionedPackage, flags: Int): PackageInfo {
        return packageManager.getPackageInfo(versionedPackage, flags)
    }

    override fun currentToCanonicalPackageNames(packageNames: Array<out String>): Array<String> {
        return packageManager.currentToCanonicalPackageNames(packageNames)
    }

    override fun canonicalToCurrentPackageNames(packageNames: Array<out String>): Array<String> {
        return packageManager.canonicalToCurrentPackageNames(packageNames)
    }

    override fun getLaunchIntentForPackage(packageName: String): Intent? {
        return packageManager.getLaunchIntentForPackage(packageName)
    }

    override fun getLeanbackLaunchIntentForPackage(packageName: String): Intent? {
        return packageManager.getLeanbackLaunchIntentForPackage(packageName)
    }

    override fun getPackageGids(packageName: String): IntArray {
        return packageManager.getPackageGids(packageName)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getPackageGids(packageName: String, flags: Int): IntArray {
        return packageManager.getPackageGids(packageName, flags)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getPackageUid(packageName: String, flags: Int): Int {
        return packageManager.getPackageUid(packageName, flags)
    }

    override fun getPermissionInfo(permName: String, flags: Int): PermissionInfo {
        return packageManager.getPermissionInfo(permName, flags)
    }

    override fun queryPermissionsByGroup(
        permissionGroup: String?,
        flags: Int
    ): MutableList<PermissionInfo> {
        return packageManager.queryPermissionsByGroup(permissionGroup, flags)
    }

    override fun getPermissionGroupInfo(groupName: String, flags: Int): PermissionGroupInfo {
        return packageManager.getPermissionGroupInfo(groupName, flags)
    }

    override fun getAllPermissionGroups(flags: Int): MutableList<PermissionGroupInfo> {
        return packageManager.getAllPermissionGroups(flags)
    }

    override fun getApplicationInfo(packageName: String, flags: Int): ApplicationInfo {
        return packageManager.getApplicationInfo(packageName, flags)
    }

    override fun getActivityInfo(component: ComponentName, flags: Int): ActivityInfo {
        return packageManager.getActivityInfo(component, flags)
    }

    override fun getReceiverInfo(component: ComponentName, flags: Int): ActivityInfo {
        return packageManager.getReceiverInfo(component, flags)
    }

    override fun getServiceInfo(component: ComponentName, flags: Int): ServiceInfo {
        return packageManager.getServiceInfo(component, flags)
    }

    override fun getProviderInfo(component: ComponentName, flags: Int): ProviderInfo {
        return packageManager.getProviderInfo(component, flags)
    }

    override fun getInstalledPackages(flags: Int): MutableList<PackageInfo> {
        if (isBlack) return mutableListOf()
        LogUtils.dTag(TAG, "getInstalledPackages")
        return packageManager.getInstalledPackages(flags)
    }

    override fun getPackagesHoldingPermissions(
        permissions: Array<out String>,
        flags: Int
    ): MutableList<PackageInfo> {
        return packageManager.getPackagesHoldingPermissions(permissions, flags)
    }

    override fun checkPermission(permName: String, packageName: String): Int {
        return packageManager.checkPermission(permName, packageName)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun isPermissionRevokedByPolicy(permName: String, packageName: String): Boolean {
        return packageManager.isPermissionRevokedByPolicy(permName, packageName)
    }

    override fun addPermission(info: PermissionInfo): Boolean {
        return packageManager.addPermission(info)
    }

    override fun addPermissionAsync(info: PermissionInfo): Boolean {
        return packageManager.addPermissionAsync(info)
    }

    override fun removePermission(permName: String) {
        packageManager.removePermission(permName)
    }

    override fun checkSignatures(packageName1: String, packageName2: String): Int {
        return packageManager.checkSignatures(packageName1, packageName2)
    }

    override fun checkSignatures(uid1: Int, uid2: Int): Int {
        return packageManager.checkSignatures(uid1, uid2)
    }

    override fun getPackagesForUid(uid: Int): Array<String>? {
        return packageManager.getPackagesForUid(uid)
    }

    override fun getNameForUid(uid: Int): String? {
        return packageManager.getNameForUid(uid)
    }

    override fun getInstalledApplications(flags: Int): MutableList<ApplicationInfo> {
        if (isBlack) return mutableListOf()
        LogUtils.dTag(TAG, "getInstalledApplications")
        return packageManager.getInstalledApplications(flags)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun isInstantApp(): Boolean {
        return packageManager.isInstantApp
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun isInstantApp(packageName: String): Boolean {
        return packageManager.isInstantApp(packageName)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getInstantAppCookieMaxBytes(): Int {
        return packageManager.getInstantAppCookieMaxBytes()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getInstantAppCookie(): ByteArray {
        return packageManager.getInstantAppCookie()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun clearInstantAppCookie() {
        packageManager.clearInstantAppCookie()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun updateInstantAppCookie(cookie: ByteArray?) {
        packageManager.updateInstantAppCookie(cookie)
    }

    override fun getSystemSharedLibraryNames(): Array<String>? {
        return packageManager.getSystemSharedLibraryNames()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getSharedLibraries(flags: Int): MutableList<SharedLibraryInfo> {
        return packageManager.getSharedLibraries(flags)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getChangedPackages(sequenceNumber: Int): ChangedPackages? {
        return packageManager.getChangedPackages(sequenceNumber)
    }

    override fun getSystemAvailableFeatures(): Array<FeatureInfo> {
        return packageManager.getSystemAvailableFeatures()
    }

    override fun hasSystemFeature(featureName: String): Boolean {
        return packageManager.hasSystemFeature(featureName)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun hasSystemFeature(featureName: String, version: Int): Boolean {
        return packageManager.hasSystemFeature(featureName, version)
    }

    override fun resolveActivity(intent: Intent, flags: Int): ResolveInfo? {
        return packageManager.resolveActivity(intent, flags)
    }

    override fun queryIntentActivities(intent: Intent, flags: Int): MutableList<ResolveInfo> {
        return packageManager.queryIntentActivities(intent, flags)
    }

    override fun queryIntentActivityOptions(
        caller: ComponentName?,
        specifics: Array<out Intent>?,
        intent: Intent,
        flags: Int
    ): MutableList<ResolveInfo> {
        return packageManager.queryIntentActivityOptions(caller, specifics, intent, flags)
    }

    override fun queryBroadcastReceivers(intent: Intent, flags: Int): MutableList<ResolveInfo> {
        return packageManager.queryBroadcastReceivers(intent, flags)
    }

    override fun resolveService(intent: Intent, flags: Int): ResolveInfo? {
        return packageManager.resolveService(intent, flags)
    }

    override fun queryIntentServices(intent: Intent, flags: Int): MutableList<ResolveInfo> {
        return packageManager.queryIntentServices(intent, flags)
    }

    override fun queryIntentContentProviders(intent: Intent, flags: Int): MutableList<ResolveInfo> {
        return packageManager.queryIntentContentProviders(intent, flags)
    }

    override fun resolveContentProvider(authority: String, flags: Int): ProviderInfo? {
        return packageManager.resolveContentProvider(authority, flags)
    }

    override fun queryContentProviders(
        processName: String?,
        uid: Int,
        flags: Int
    ): MutableList<ProviderInfo> {
        return packageManager.queryContentProviders(processName, uid, flags)
    }

    override fun getInstrumentationInfo(className: ComponentName, flags: Int): InstrumentationInfo {
        return packageManager.getInstrumentationInfo(className, flags)
    }

    override fun queryInstrumentation(
        targetPackage: String,
        flags: Int
    ): MutableList<InstrumentationInfo> {
        return packageManager.queryInstrumentation(targetPackage, flags)
    }

    override fun getDrawable(
        packageName: String,
        resid: Int,
        appInfo: ApplicationInfo?
    ): Drawable? {
        return packageManager.getDrawable(packageName, resid, appInfo)
    }

    override fun getActivityIcon(activityName: ComponentName): Drawable {
        return packageManager.getActivityIcon(activityName)
    }

    override fun getActivityIcon(intent: Intent): Drawable {
        return packageManager.getActivityIcon(intent)
    }

    override fun getActivityBanner(activityName: ComponentName): Drawable? {
        return packageManager.getActivityBanner(activityName)
    }

    override fun getActivityBanner(intent: Intent): Drawable? {
        return packageManager.getActivityBanner(intent)
    }

    override fun getDefaultActivityIcon(): Drawable {
        return packageManager.defaultActivityIcon
    }

    override fun getApplicationIcon(info: ApplicationInfo): Drawable {
        return packageManager.getApplicationIcon(info)
    }

    override fun getApplicationIcon(packageName: String): Drawable {
        return packageManager.getApplicationIcon(packageName)
    }

    override fun getApplicationBanner(info: ApplicationInfo): Drawable? {
        return packageManager.getApplicationBanner(info)
    }

    override fun getApplicationBanner(packageName: String): Drawable? {
        return packageManager.getApplicationBanner(packageName)
    }

    override fun getActivityLogo(activityName: ComponentName): Drawable? {
        return packageManager.getActivityLogo(activityName)
    }

    override fun getActivityLogo(intent: Intent): Drawable? {
        return packageManager.getActivityLogo(intent)
    }

    override fun getApplicationLogo(info: ApplicationInfo): Drawable? {
        return packageManager.getApplicationLogo(info)
    }

    override fun getApplicationLogo(packageName: String): Drawable? {
        return packageManager.getApplicationLogo(packageName)
    }

    override fun getUserBadgedIcon(drawable: Drawable, user: UserHandle): Drawable {
        return packageManager.getUserBadgedIcon(drawable, user)
    }

    override fun getUserBadgedDrawableForDensity(
        drawable: Drawable,
        user: UserHandle,
        badgeLocation: Rect?,
        badgeDensity: Int
    ): Drawable {
        return packageManager.getUserBadgedDrawableForDensity(
            drawable,
            user,
            badgeLocation,
            badgeDensity
        )
    }

    override fun getUserBadgedLabel(label: CharSequence, user: UserHandle): CharSequence {
        return packageManager.getUserBadgedLabel(label, user)
    }

    override fun getText(
        packageName: String,
        resid: Int,
        appInfo: ApplicationInfo?
    ): CharSequence? {
        return packageManager.getText(packageName, resid, appInfo)
    }

    override fun getXml(
        packageName: String,
        resid: Int,
        appInfo: ApplicationInfo?
    ): XmlResourceParser? {
        return packageManager.getXml(packageName, resid, appInfo)
    }

    override fun getApplicationLabel(info: ApplicationInfo): CharSequence {
        return packageManager.getApplicationLabel(info)
    }

    override fun getResourcesForActivity(activityName: ComponentName): Resources {
        return packageManager.getResourcesForActivity(activityName)
    }

    override fun getResourcesForApplication(app: ApplicationInfo): Resources {
        return packageManager.getResourcesForApplication(app)
    }

    override fun getResourcesForApplication(packageName: String): Resources {
        return packageManager.getResourcesForApplication(packageName)
    }

    override fun verifyPendingInstall(id: Int, verificationCode: Int) {
        packageManager.verifyPendingInstall(id, verificationCode)
    }

    override fun extendVerificationTimeout(
        id: Int,
        verificationCodeAtTimeout: Int,
        millisecondsToDelay: Long
    ) {
        packageManager.extendVerificationTimeout(id, verificationCodeAtTimeout, millisecondsToDelay)
    }

    override fun setInstallerPackageName(targetPackage: String, installerPackageName: String?) {
        packageManager.setInstallerPackageName(targetPackage, installerPackageName)
    }

    override fun getInstallerPackageName(packageName: String): String? {
        return packageManager.getInstallerPackageName(packageName)
    }

    override fun addPackageToPreferred(packageName: String) {
        packageManager.addPackageToPreferred(packageName)
    }

    override fun removePackageFromPreferred(packageName: String) {
        packageManager.removePackageFromPreferred(packageName)
    }

    override fun getPreferredPackages(flags: Int): MutableList<PackageInfo> {
        return packageManager.getPreferredPackages(flags)
    }

    override fun addPreferredActivity(
        filter: IntentFilter,
        match: Int,
        set: Array<out ComponentName>?,
        activity: ComponentName
    ) {
        packageManager.addPreferredActivity(filter, match, set, activity)
    }

    override fun clearPackagePreferredActivities(packageName: String) {
        packageManager.clearPackagePreferredActivities(packageName)
    }

    override fun getPreferredActivities(
        outFilters: MutableList<IntentFilter>,
        outActivities: MutableList<ComponentName>,
        packageName: String?
    ): Int {
        return packageManager.getPreferredActivities(outFilters, outActivities, packageName)
    }

    override fun setComponentEnabledSetting(
        componentName: ComponentName,
        newState: Int,
        flags: Int
    ) {
        packageManager.setComponentEnabledSetting(componentName, newState, flags)
    }

    override fun getComponentEnabledSetting(componentName: ComponentName): Int {
        return packageManager.getComponentEnabledSetting(componentName)
    }

    override fun setApplicationEnabledSetting(packageName: String, newState: Int, flags: Int) {
        packageManager.setApplicationEnabledSetting(packageName, newState, flags)
    }

    override fun getApplicationEnabledSetting(packageName: String): Int {
        return packageManager.getApplicationEnabledSetting(packageName)
    }

    override fun isSafeMode(): Boolean {
        return packageManager.isSafeMode()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setApplicationCategoryHint(packageName: String, categoryHint: Int) {
        packageManager.setApplicationCategoryHint(packageName, categoryHint)
    }

    override fun getPackageInstaller(): PackageInstaller {
        return packageManager.packageInstaller
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun canRequestPackageInstalls(): Boolean {
        return packageManager.canRequestPackageInstalls()
    }

//    @UnsupportedAppUsage
//    override fun loadItemIcon(
//        itemInfo: PackageItemInfo,
//        appInfo: ApplicationInfo?
//    ): Drawable? {
//        return packageManager.loadItemIcon(itemInfo, appInfo)
//    }

}