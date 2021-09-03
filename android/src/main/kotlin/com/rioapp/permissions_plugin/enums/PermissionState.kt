package com.rioapp.permissions_plugin.enums

enum class PermissionState {
    GRANTED,
    DENIED,
    UNKNOWN;

    fun toCode(): Int {
       return this.ordinal
    }
}