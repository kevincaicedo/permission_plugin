package com.rioapp.permissions_plugin.enums

import android.Manifest

enum class PermissionsName constructor(private val manifestName: String) {

    READ_CALENDAR(Manifest.permission.READ_CALENDAR),
    WRITE_CALENDAR(Manifest.permission.WRITE_CALENDAR),
    READ_CALL_LOG(Manifest.permission.READ_CALL_LOG),
    WRITE_CALL_LOG(Manifest.permission.WRITE_CALL_LOG),
    PROCESS_OUTGOING_CALLS(Manifest.permission.PROCESS_OUTGOING_CALLS),
    CAMERA(Manifest.permission.CAMERA),
    READ_CONTACTS(Manifest.permission.READ_CONTACTS),
    WRITE_CONTACTS(Manifest.permission.WRITE_CONTACTS),
    GET_ACCOUNTS(Manifest.permission.GET_ACCOUNTS),
    ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION),
    ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION),
    RECORD_AUDIO(Manifest.permission.RECORD_AUDIO),
    READ_PHONE_STATE(Manifest.permission.READ_PHONE_STATE),
    CALL_PHONE(Manifest.permission.CALL_PHONE),
    ADD_VOICEMAIL(Manifest.permission.ADD_VOICEMAIL),
    USE_SIP(Manifest.permission.USE_SIP),
    BODY_SENSORS(Manifest.permission.BODY_SENSORS),
    SEND_SMS(Manifest.permission.SEND_SMS),
    RECEIVE_SMS(Manifest.permission.RECEIVE_SMS),
    READ_SMS(Manifest.permission.READ_SMS),
    RECEIVE_WAP_PUSH(Manifest.permission.RECEIVE_WAP_PUSH),
    RECEIVE_MMS(Manifest.permission.RECEIVE_MMS),
    READ_EXTERNAL_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE),
    WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE),
    UNKNOWN("UNKNOWN");

    fun toManifestNames(): String {
        return this.manifestName
    }

}