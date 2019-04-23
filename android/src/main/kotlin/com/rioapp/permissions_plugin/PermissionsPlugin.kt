package com.rioapp.permissions_plugin

import android.app.Activity
import com.rioapp.permissions_plugin.enums.CommandName
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.rioapp.permissions_plugin.enums.PermissionsName
import com.rioapp.permissions_plugin.enums.toCode
import androidx.core.content.ContextCompat

class PermissionsPlugin(private val activity: Activity) : MethodCallHandler, PluginRegistry.RequestPermissionsResultListener {

    private var mResult: Result? = null
    private val CODE_REQUEST_PERMISSION = 5991

    companion object {

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "permissions_plugin")
            val permissionsPlugin = PermissionsPlugin(registrar.activity())
            channel.setMethodCallHandler(permissionsPlugin)
            registrar.addRequestPermissionsResultListener(permissionsPlugin)
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        mResult = result

        when {
            call.method == "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
            call.method == CommandName.REQUEST_PERMISSIONS.toString() -> requestPermissions(call.arguments)
            call.method == CommandName.CHECK_PERMISSIONS.toString() -> checkPermissions(call.arguments)
            else -> result.notImplemented()
        }
    }


    private fun requestPermissions(arguments: Any) {
        val permissionsGroup = (arguments as ArrayList<*>)
                .map { value -> PermissionsName.valueOf(value as String).toManifestNames() }.toTypedArray()
        if (permissionsGroup.isNotEmpty())
            ActivityCompat.requestPermissions(activity, permissionsGroup, CODE_REQUEST_PERMISSION)
        else
            mResult?.success(permissionsGroup)
    }

    private fun checkPermissions(arguments: Any) {
        val permissionsGroup = (arguments as ArrayList<*>)
                .map { value -> PermissionsName.valueOf(value as String).toManifestNames() }.toTypedArray()

        if (permissionsGroup.isEmpty()) {
            mResult?.success(permissionsGroup)
            return
        }

        val permissionsResult = HashMap<Int, Int>()

        for (name in permissionsGroup) {
            permissionsResult[toCode(name)] =  Math.abs( ContextCompat.checkSelfPermission(activity, name) )
        }

        mResult?.success(permissionsResult)
    }

    fun checkPermissionManifest() {
        val permissionsManifest = this.activity.packageManager
                .getPackageInfo(activity.packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, results: IntArray?): Boolean {
        if (requestCode == CODE_REQUEST_PERMISSION) {
            permissionResponse(permissions, results)
            return true
        }

        return false
    }
}
