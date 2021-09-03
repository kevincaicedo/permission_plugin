package com.rioapp.permissions_plugin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
import android.util.SparseIntArray
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import com.rioapp.permissions_plugin.enums.PermissionState
import com.rioapp.permissions_plugin.enums.PermissionsName
import com.rioapp.permissions_plugin.utils.PermissionsNameUtils
import com.rioapp.permissions_plugin.utils.Utils
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry
import kotlin.math.abs

class PermissionsPluginDelegate(private val activity: Activity): PluginRegistry.RequestPermissionsResultListener, PluginRegistry.ActivityResultListener {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (requestCode == CODE_REQUEST_CHECK_BATTERY) {
            mResult?.success( if (resultCode == Activity.RESULT_OK) 0 else 1)
            return true
        }

        return false
    }

    private var mResult: MethodChannel.Result? = null
    private val mPermissionList = SparseIntArray()
    private val CODE_REQUEST_PERMISSION = 2358
    private val CODE_REQUEST_CHECK_BATTERY = 3930

    //    private val mPermissionsManifest: ArrayList<String> = arrayListOf()
    //
    //    fun checkPermissionManifest() {
    //        val permissionsManifest = this.activity.packageManager
    //                .getPackageInfo(activity.packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
    //    }

    fun requestIgnoreBatteryOptimization(result: MethodChannel.Result) {
        mResult = result

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            intent.data = Uri.parse("package:${activity.packageName}" )
            startActivityForResult(activity, intent, CODE_REQUEST_CHECK_BATTERY, null)
        } else {
            result.success(PermissionState.GRANTED)
        }
    }

    fun isIgnoreBatteryOptimization(result: MethodChannel.Result) {
        val powerManager = activity.getSystemService(Context.POWER_SERVICE) as PowerManager
        val name = activity.packageName

        val isAppWhiteListBattery = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            powerManager.isIgnoringBatteryOptimizations(name)
        else
            true

        val res: Int = if (isAppWhiteListBattery) 0 else 1
        result.success(res)
    }

    private fun validatePermission(permissionsGroup: Array<String>): Array<String> {

        if(mPermissionList.size() > 0) mPermissionList.clear()

        val mPermissionsGroup = arrayListOf<String>()

        for (permissionGroup in permissionsGroup){
            if(permissionGroup == PermissionsName.BODY_SENSORS.toManifestNames()) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT)
                    mPermissionList.append(PermissionsName.BODY_SENSORS.ordinal, PermissionState.DENIED.toCode())
                else {
                    mPermissionList.append(PermissionsNameUtils.toPermissionName(permissionGroup).ordinal,
                            PermissionState.UNKNOWN.toCode())
                    mPermissionsGroup.add(permissionGroup)
                }
            } else {
                mPermissionList.append(PermissionsNameUtils.toPermissionName(permissionGroup).ordinal,
                        PermissionState.UNKNOWN.toCode())
                mPermissionsGroup.add(permissionGroup)
            }
        }

        return mPermissionsGroup.toTypedArray()
    }

    fun requestPermission(arguments: Any, mResult: MethodChannel.Result) {
        this.mResult = mResult
        var permissionsGroup: Array<String> = (arguments as ArrayList<*>)
                .map { value -> PermissionsName.valueOf(value as String).toManifestNames() }.toTypedArray()

        permissionsGroup = this.validatePermission(permissionsGroup)
        ActivityCompat.requestPermissions(activity, permissionsGroup, CODE_REQUEST_PERMISSION)
    }

    fun checkPermissions(arguments: Any, mResult: MethodChannel.Result) {
        var permissionsGroup = (arguments as ArrayList<*>)
                .map { value -> PermissionsName.valueOf(value as String).toManifestNames() }.toTypedArray()

        permissionsGroup = this.validatePermission(permissionsGroup)

        for (name in permissionsGroup)
            if(mPermissionList.indexOfKey(PermissionsNameUtils.toPermissionName(name).ordinal) >= 0)
                mPermissionList.put(PermissionsNameUtils.toCode(name), abs(ContextCompat.checkSelfPermission(activity, name)))

        mResult.success(Utils.asMap(mPermissionList))
    }

    private fun permissionResponse(permissions: Array<out String>?, results: IntArray?) {

        if (permissions == null || permissions.isEmpty()) {
            mResult?.success(Utils.asMap(mPermissionList))
            return
        }

        for ((index, permission) in permissions.withIndex())
            if(mPermissionList.indexOfKey(PermissionsNameUtils.toPermissionName(permission).ordinal) >= 0)
                mPermissionList.put(PermissionsNameUtils.toCode(permission), abs(results?.get(index)!!))

        mResult?.success(Utils.asMap(mPermissionList))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, results: IntArray?): Boolean {
        if (requestCode == CODE_REQUEST_PERMISSION) {
            permissionResponse(permissions, results)
            return true
        }

        return false
    }
}