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
    READ_PHONE_NUMBERS(Manifest.permission.READ_PHONE_NUMBERS),
    CALL_PHONE(Manifest.permission.CALL_PHONE),
    ANSWER_PHONE_CALLS(Manifest.permission.ANSWER_PHONE_CALLS),
    ADD_VOICEMAIL(Manifest.permission.ADD_VOICEMAIL),
    USE_SIP(Manifest.permission.USE_SIP),
    BODY_SENSORS(Manifest.permission.BODY_SENSORS),
    SEND_SMS(Manifest.permission.SEND_SMS),
    RECEIVE_SMS(Manifest.permission.RECEIVE_SMS),
    READ_SMS(Manifest.permission.READ_SMS),
    RECEIVE_WAP_PUSH(Manifest.permission.RECEIVE_WAP_PUSH),
    RECEIVE_MMS(Manifest.permission.RECEIVE_MMS),
    READ_EXTERNAL_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE),
    WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    fun toManifestNames(): String {
        return this.manifestName
    }

//    fun toManifestNames(): String {
//        when(this.toString()) {
//            PermissionsName.READ_CALENDAR.toString() -> return Manifest.permission.READ_CALENDAR
//            PermissionsName.WRITE_CALENDAR.toString() -> return Manifest.permission.WRITE_CALENDAR
//            PermissionsName.READ_CALL_LOG.toString() -> return Manifest.permission.READ_CALL_LOG
//            PermissionsName.WRITE_CALL_LOG.toString() -> return Manifest.permission.WRITE_CALL_LOG
//            PermissionsName.PROCESS_OUTGOING_CALLS.toString() -> return Manifest.permission.PROCESS_OUTGOING_CALLS
//            PermissionsName.CAMERA.toString() -> return Manifest.permission.CAMERA
//            PermissionsName.READ_CONTACTS.toString() -> return Manifest.permission.READ_CONTACTS
//            PermissionsName.WRITE_CONTACTS.toString() -> return Manifest.permission.WRITE_CONTACTS
//            PermissionsName.GET_ACCOUNTS.toString() -> return Manifest.permission.GET_ACCOUNTS
//            PermissionsName.ACCESS_FINE_LOCATION.toString() -> return Manifest.permission.ACCESS_FINE_LOCATION
//            PermissionsName.ACCESS_COARSE_LOCATION.toString() -> return Manifest.permission.ACCESS_COARSE_LOCATION
//            PermissionsName.RECORD_AUDIO.toString() -> return Manifest.permission.RECORD_AUDIO
//            PermissionsName.READ_PHONE_STATE.toString() -> return Manifest.permission.READ_PHONE_STATE
//            PermissionsName.READ_PHONE_NUMBERS.toString() -> return Manifest.permission.READ_PHONE_NUMBERS
//            PermissionsName.CALL_PHONE.toString() -> return Manifest.permission.CALL_PHONE
//            PermissionsName.ANSWER_PHONE_CALLS.toString() -> return Manifest.permission.ANSWER_PHONE_CALLS
//            PermissionsName.ADD_VOICEMAIL.toString() -> return Manifest.permission.ADD_VOICEMAIL
//            PermissionsName.USE_SIP.toString() -> return Manifest.permission.USE_SIP
//            PermissionsName.BODY_SENSORS.toString() -> return Manifest.permission.BODY_SENSORS
//            PermissionsName.SEND_SMS.toString() -> return Manifest.permission.SEND_SMS
//            PermissionsName.RECEIVE_SMS.toString() -> return Manifest.permission.RECEIVE_SMS
//            PermissionsName.READ_SMS.toString() -> return Manifest.permission.READ_SMS
//            PermissionsName.RECEIVE_WAP_PUSH.toString() -> return Manifest.permission.RECEIVE_WAP_PUSH
//            PermissionsName.RECEIVE_MMS.toString() -> return Manifest.permission.RECEIVE_MMS
//            PermissionsName.READ_EXTERNAL_STORAGE.toString() -> return Manifest.permission.READ_EXTERNAL_STORAGE
//            else -> return this.toString()
//        }
//    }

}

fun toCode(permission: String): Int {
    when(permission) {
        Manifest.permission.READ_CALENDAR -> return PermissionsName.READ_CALENDAR.ordinal
        Manifest.permission.WRITE_CALENDAR -> return PermissionsName.WRITE_CALENDAR.ordinal
        Manifest.permission.READ_CALL_LOG -> return PermissionsName.READ_CALL_LOG.ordinal
        Manifest.permission.WRITE_CALL_LOG -> return PermissionsName.WRITE_CALL_LOG.ordinal
        Manifest.permission.PROCESS_OUTGOING_CALLS -> return PermissionsName.PROCESS_OUTGOING_CALLS.ordinal
        Manifest.permission.CAMERA -> return PermissionsName.CAMERA.ordinal
        Manifest.permission.READ_CONTACTS -> return PermissionsName.READ_CONTACTS.ordinal
        Manifest.permission.WRITE_CONTACTS -> return PermissionsName.WRITE_CONTACTS.ordinal
        Manifest.permission.GET_ACCOUNTS -> return PermissionsName.GET_ACCOUNTS.ordinal
        Manifest.permission.ACCESS_FINE_LOCATION -> return PermissionsName.ACCESS_FINE_LOCATION.ordinal
        Manifest.permission.ACCESS_COARSE_LOCATION -> return PermissionsName.ACCESS_COARSE_LOCATION.ordinal
        Manifest.permission.RECORD_AUDIO -> return PermissionsName.RECORD_AUDIO.ordinal
        Manifest.permission.READ_PHONE_STATE -> return PermissionsName.READ_PHONE_STATE.ordinal
        Manifest.permission.READ_PHONE_NUMBERS -> return PermissionsName.READ_PHONE_NUMBERS.ordinal
        Manifest.permission.CALL_PHONE -> return PermissionsName.CALL_PHONE.ordinal
        Manifest.permission.ANSWER_PHONE_CALLS -> return PermissionsName.ANSWER_PHONE_CALLS.ordinal
        Manifest.permission.ADD_VOICEMAIL -> return PermissionsName.ADD_VOICEMAIL.ordinal
        Manifest.permission.USE_SIP -> return PermissionsName.USE_SIP.ordinal
        Manifest.permission.BODY_SENSORS -> return PermissionsName.BODY_SENSORS.ordinal
        Manifest.permission.SEND_SMS -> return PermissionsName.SEND_SMS.ordinal
        Manifest.permission.RECEIVE_SMS -> return PermissionsName.RECEIVE_SMS.ordinal
        Manifest.permission.READ_SMS -> return PermissionsName.READ_SMS.ordinal
        Manifest.permission.RECEIVE_WAP_PUSH -> return PermissionsName.RECEIVE_WAP_PUSH.ordinal
        Manifest.permission.RECEIVE_MMS -> return PermissionsName.RECEIVE_MMS.ordinal
        Manifest.permission.READ_EXTERNAL_STORAGE -> return PermissionsName.READ_EXTERNAL_STORAGE.ordinal
        Manifest.permission.WRITE_EXTERNAL_STORAGE -> return PermissionsName.WRITE_EXTERNAL_STORAGE.ordinal
        else -> return -1
    }
}