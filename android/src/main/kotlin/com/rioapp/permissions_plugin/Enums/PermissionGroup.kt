package com.rioapp.permissions_plugin.Enums

enum class PermissionGroup constructor(private val `val`: Int) {
    CALENDAR(0),
    CALL_LOG(1),
    CAMERA(2),
    CONTACTS(3),
    LOCATION(4),
    MICROPHONE(5),
    PHONE(6),
    SENSORS(7),
    SMS(8),
    STORAGE(9);

    fun toInt(): Int {
        return this.`val`
    }
}