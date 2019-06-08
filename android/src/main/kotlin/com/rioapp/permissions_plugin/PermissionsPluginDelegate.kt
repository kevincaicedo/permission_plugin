package com.rioapp.permissions_plugin

import android.app.Activity
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.rioapp.permissions_plugin.enums.PermissionsName
import com.rioapp.permissions_plugin.enums.toCode
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry

class PermissionsPluginDelegate(private val activity: Activity): PluginRegistry.RequestPermissionsResultListener {
    private var mResult: MethodChannel.Result? = null
    private val CODE_REQUEST_PERMISSION = 2358

//    fun checkPermissionManifest() {
//        val permissionsManifest = this.activity.packageManager
//                .getPackageInfo(activity.packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
//    }

    fun requestPermission(arguments: Any, mResult: MethodChannel.Result) {
        this.mResult = mResult
        val permissionsGroup = (arguments as ArrayList<*>)
                .map { value -> PermissionsName.valueOf(value as String).toManifestNames() }.toTypedArray()

        if (permissionsGroup.isNotEmpty() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            ActivityCompat.requestPermissions(activity, permissionsGroup, CODE_REQUEST_PERMISSION)
        else
            mResult.success(permissionsGroup)
    }

    fun checkPermissions(arguments: Any, mResult: MethodChannel.Result) {
        this.mResult = mResult
        val permissionsGroup = (arguments as ArrayList<*>)
                .map { value -> PermissionsName.valueOf(value as String).toManifestNames() }.toTypedArray()

        if (permissionsGroup.isEmpty()) {
            mResult.success(permissionsGroup)
            return
        }

        val permissionsResult = HashMap<Int, Int>()
        for (name in permissionsGroup) {
            permissionsResult[toCode(name)] =  Math.abs( ContextCompat.checkSelfPermission(activity, name) )
        }

        mResult.success(permissionsResult)
    }

    private fun permissionResponse(permissions: Array<out String>?, results: IntArray?) {

        val permissionsResult = HashMap<Int, Int>()
        if (permissions == null || permissions.isEmpty()) {
            mResult?.success(permissionsResult)
            return
        }

        for ((index, permission) in permissions.withIndex())
            permissionsResult[toCode(permission)] = Math.abs(results?.get(index)!!)

        mResult?.success(permissionsResult)
    }

    // int requestCode, String[] permissions, int[] grantResults
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, results: IntArray?): Boolean {
        if (requestCode == CODE_REQUEST_PERMISSION) {
            permissionResponse(permissions, results)
            return true
        }

        return false
    }
}