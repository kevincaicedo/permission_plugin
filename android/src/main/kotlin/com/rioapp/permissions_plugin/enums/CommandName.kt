package com.rioapp.permissions_plugin.enums

enum class CommandName constructor(private val `val`: String) {
    REQUEST_PERMISSIONS( "request-permissions"),
    REQUEST_BATTERY_OPTIMIZATION( "request-battery-optimization"),
    CHECK_BATTERY_OPTIMIZATION("check-battery-optimization"),
    CHECK_PERMISSIONS( "check-permissions");

    override fun toString(): String {
        return this.`val`
    }
}