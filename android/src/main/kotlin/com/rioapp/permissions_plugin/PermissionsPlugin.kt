package com.rioapp.permissions_plugin

import android.Manifest
import android.app.Activity
import com.rioapp.permissions_plugin.Enums.CommandName
import com.rioapp.permissions_plugin.Enums.PermissionGroup
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


class PermissionsPlugin(private val activity: Activity) : MethodCallHandler, PluginRegistry.RequestPermissionsResultListener {

    private var mResult: Result? = null
    private val CODE_REQUEST_PERMISSION = 199599

    companion object {

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "permissions_plugin")
            val permissionsPlugin = PermissionsPlugin(registrar.activity())
            channel.setMethodCallHandler(permissionsPlugin)
            registrar.addRequestPermissionsResultListener(permissionsPlugin)
        }
        @JvmStatic
        val PERMISSION_GROUP: HashMap<String, String> = hashMapOf(
                PermissionGroup.CALENDAR.toString() to Manifest.permission_group.CALENDAR,
                PermissionGroup.CALL_LOG.toString() to Manifest.permission_group.CALL_LOG,
                PermissionGroup.CAMERA.toString() to Manifest.permission_group.CAMERA,
                PermissionGroup.CONTACTS.toString() to Manifest.permission_group.CONTACTS,
                PermissionGroup.LOCATION.toString() to Manifest.permission_group.LOCATION,
                PermissionGroup.MICROPHONE.toString() to Manifest.permission_group.MICROPHONE,
                PermissionGroup.PHONE.toString() to Manifest.permission_group.PHONE,
                PermissionGroup.SENSORS.toString() to Manifest.permission_group.SENSORS,
                PermissionGroup.SMS.toString() to Manifest.permission_group.SMS,
                PermissionGroup.STORAGE.toString() to Manifest.permission_group.STORAGE
        )
        @JvmStatic
        val PERMISSION_GROUP_CODE: HashMap<String, Int> = hashMapOf(
                Manifest.permission_group.CALENDAR to PermissionGroup.CALENDAR.toInt(),
                Manifest.permission_group.CALL_LOG to PermissionGroup.CALL_LOG.toInt(),
                Manifest.permission_group.CAMERA to PermissionGroup.CAMERA.toInt(),
                Manifest.permission_group.CONTACTS to PermissionGroup.CONTACTS.toInt(),
                Manifest.permission_group.LOCATION to PermissionGroup.LOCATION.toInt(),
                Manifest.permission_group.MICROPHONE to PermissionGroup.MICROPHONE.toInt(),
                Manifest.permission_group.PHONE to PermissionGroup.PHONE.toInt(),
                Manifest.permission_group.SENSORS to PermissionGroup.SENSORS.toInt(),
                Manifest.permission_group.SMS to PermissionGroup.SMS.toInt(),
                Manifest.permission_group.STORAGE to PermissionGroup.STORAGE.toInt()
        )
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


    fun requestPermissions(arguments: Any){
        // val permissions = arrayOf(arguments)
        val permissionsGroup = (arguments as ArrayList<*>).map { value -> PERMISSION_GROUP[value] }.toTypedArray()
        if (permissionsGroup.isNotEmpty())
            ActivityCompat.requestPermissions(activity, permissionsGroup, CODE_REQUEST_PERMISSION)
        else
            mResult?.success(permissionsGroup)
    }

    fun checkPermissions(arguments: Any){
        val permissions = arrayOf(arguments)
        val permissions_group = permissions.map { value -> PERMISSION_GROUP[value] }
    }

    fun checkPermissionManifest(){
        val permissionsManifest = this.activity.packageManager.getPackageInfo(activity.packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
    }

    fun permissionResponse(permissions: Array<out String>?, results: IntArray?){

        val permissionsResult = HashMap<Int, Int>()
        if ( permissions == null || permissions.isEmpty() ) {
            mResult?.success(permissionsResult)
            return
        }

        for ( (index, permission ) in permissions.withIndex())
            permissionsResult[PERMISSION_GROUP_CODE[permission]!!] = results?.get(index)!!

        mResult?.success(permissionsResult)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, results: IntArray?): Boolean {
        if (requestCode == CODE_REQUEST_PERMISSION){
            permissionResponse(permissions, results)
            return true
        }

        return false
    }
}
