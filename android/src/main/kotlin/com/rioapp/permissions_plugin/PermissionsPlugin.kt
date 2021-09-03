package com.rioapp.permissions_plugin

import android.os.Build
import com.rioapp.permissions_plugin.enums.CommandName
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class PermissionsPlugin(private val delegate: PermissionsPluginDelegate) : MethodCallHandler {

    companion object {

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "permissions_plugin")
            val permissionsPluginDelegate = PermissionsPluginDelegate(registrar.activity())
            val permissionsPlugin = PermissionsPlugin(permissionsPluginDelegate)
            registrar.addRequestPermissionsResultListener(permissionsPluginDelegate)
            channel.setMethodCallHandler(permissionsPlugin)
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when {
            call.method == "getPlatformVersion" -> result.success("Android ${Build.VERSION.RELEASE}")
            call.method == CommandName.REQUEST_PERMISSIONS.toString() -> delegate.requestPermission(call.arguments, result)
            call.method == CommandName.CHECK_PERMISSIONS.toString() -> delegate.checkPermissions(call.arguments, result)
            else -> result.notImplemented()
        }
    }

}
