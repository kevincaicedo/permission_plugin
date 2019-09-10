package com.rioapp.permissions_plugin

import android.app.Activity
import android.os.Build
import android.util.SparseIntArray
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.rioapp.permissions_plugin.enums.PermissionState
import com.rioapp.permissions_plugin.enums.PermissionsName
import com.rioapp.permissions_plugin.utils.PermissionsNameUtils
import com.rioapp.permissions_plugin.utils.Utils
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry
import kotlin.math.abs

class PermissionsPluginDelegate(private val activity: Activity): PluginRegistry.RequestPermissionsResultListener {
    private var mResult: MethodChannel.Result? = null
    private val mPermissionList = SparseIntArray()
    private val CODE_REQUEST_PERMISSION = 2358

    //    private val mPermissionsManifest: ArrayList<String> = arrayListOf()
    //
    //    fun checkPermissionManifest() {
    //        val permissionsManifest = this.activity.packageManager
    //                .getPackageInfo(activity.packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
    //    }


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