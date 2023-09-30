package com.cesarwillymc.animeapp.util.security

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.cesarwillymc.animeapp.util.constants.ZERO
import dalvik.system.DexClassLoader
import java.io.File
import java.lang.reflect.Method

/**
 * https://gist.github.com/jaredrummler/7b7a7667643f63fcf9e334d8c16ececa
 */
object XposedUtils {

    /**
     * check in all contexts, if there are signs of xposed
     *
     * @param context
     *     The application context
     *
     */
    fun isUsedXposed(context: Context): Boolean {
        return when {
            context.getXposedVersion() -> true
            isXposedInstallerAvailable(context) -> true
            isXposedActive() -> true
            else -> getInstalledXposedPackages(context)
        }
    }

    /**
     * Get the current Xposed version installed on the device.
     *
     * @param context
     *     The application context
     * @return returns a boolean value, which if an integer version is detected returns false
     */
    private fun Context.getXposedVersion(): Boolean {
        try {
            val xposedBridge = File("/system/framework/XposedBridge.jar")
            if (xposedBridge.exists()) {
                val optimizedDir = getDir("dex", Context.MODE_PRIVATE)
                val dexClassLoader = DexClassLoader(
                    xposedBridge.path,
                    optimizedDir.path,
                    null,
                    ClassLoader.getSystemClassLoader()
                )
                val xposedBridgeClass: Class<*> =
                    dexClassLoader.loadClass("de.robv.android.xposed.XposedBridge")
                val getXposedVersion: Method = xposedBridgeClass.getDeclaredMethod("getXposedVersion")
                if (!getXposedVersion.isAccessible) getXposedVersion.isAccessible = true
                val response: Int? = getXposedVersion.invoke(null) as Int?
                return response != null
            }
        } catch (ignored: Exception) {
        }
        return false
    }

    /**
     * Check if the Xposed installer is installed and enabled on the device.
     *
     * @param context
     *     The application context
     * @return {@code true} if the package "de.robv.android.xposed.installer" is installed and enabled.
     */
    @SuppressLint("WrongConstant")
    private fun isXposedInstallerAvailable(context: Context): Boolean {
        try {
            val appInfo =
                context.packageManager.getApplicationInfo("de.robv.android.xposed.installer", ZERO)
            if (appInfo != null) {
                return appInfo.enabled
            }
        } catch (ignored: PackageManager.NameNotFoundException) {
        }
        return false
    }

    /**
     * Check if the Xposed framework is installed and active.
     *
     * @return {@code true} if Xposed is active on the device.
     */
    private fun isXposedActive(): Boolean {
        val stackTraces = Throwable().stackTrace
        for (stackTrace in stackTraces) {
            val clazzName = stackTrace.className
            if (clazzName != null && clazzName.contains("de.robv.android.xposed.XposedBridge")) {
                return true
            }
        }
        return false
    }

    /**
     * Get all currently installed Xposed modules.
     *
     * @param context The application context
     * @return A list of installed Xposed modules, returns boolean depending on whether there is existing data.
     */
    private fun getInstalledXposedPackages(context: Context): Boolean {
        val packages: ArrayList<PackageInfo> = ArrayList()
        val pm = context.packageManager
        val installedPackages = pm.getInstalledPackages(PackageManager.GET_META_DATA)
        for (installedPackage in installedPackages) {
            val metaData = installedPackage.applicationInfo.metaData
            if (metaData != null && metaData.containsKey("xposedmodule")) {
                packages.add(installedPackage)
            }
        }
        return packages.isNotEmpty()
    }
}
