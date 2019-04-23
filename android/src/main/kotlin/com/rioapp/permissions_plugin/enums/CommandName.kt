package com.rioapp.permissions_plugin.enums

enum class CommandName constructor(private val `val`: String) {
    REQUEST_PERMISSIONS( "request-permissions"),
    CHECK_PERMISSIONS( "check-permissions");

    override fun toString(): String {
        return this.`val`
    }
}