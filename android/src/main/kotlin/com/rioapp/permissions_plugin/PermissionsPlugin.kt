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

class PermissionsPlugin(activity: Activity) : MethodCallHandler, PluginRegistry.RequestPermissionsResultListener {

    private var mResult: Result? = null

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

    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        mResult = result

        when {
            call.method == "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
            call.method == CommandName.REQUEST_PERMISSIONS.toString() -> requestPermissions(call.arguments)
            call.method == CommandName.CHECK_PERMISSIONS.toString() -> result.success("Android")
            else -> result.notImplemented()
        }
    }


    fun requestPermissions(arguments: Any){
        val permissions = arrayOf(arguments)

    }

    override fun onRequestPermissionsResult(p0: Int, p1: Array<out String>?, p2: IntArray?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
