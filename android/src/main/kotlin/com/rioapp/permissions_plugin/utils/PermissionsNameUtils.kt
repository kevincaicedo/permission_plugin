package com.rioapp.permissions_plugin.utils

import android.Manifest
import com.rioapp.permissions_plugin.enums.PermissionsName

abstract class PermissionsNameUtils {

    companion object {
        @JvmStatic
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
                Manifest.permission.CALL_PHONE -> return PermissionsName.CALL_PHONE.ordinal
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
                else -> return PermissionsName.UNKNOWN.ordinal
            }
        }

        @JvmStatic
        fun toPermissionName(manifestPermission: String): PermissionsName {
            when(manifestPermission) {
                Manifest.permission.READ_CALENDAR -> return PermissionsName.READ_CALENDAR
                Manifest.permission.WRITE_CALENDAR -> return PermissionsName.WRITE_CALENDAR
                Manifest.permission.READ_CALL_LOG -> return PermissionsName.READ_CALL_LOG
                Manifest.permission.WRITE_CALL_LOG -> return PermissionsName.WRITE_CALL_LOG
                Manifest.permission.PROCESS_OUTGOING_CALLS -> return PermissionsName.PROCESS_OUTGOING_CALLS
                Manifest.permission.CAMERA -> return PermissionsName.CAMERA
                Manifest.permission.READ_CONTACTS -> return PermissionsName.READ_CONTACTS
                Manifest.permission.WRITE_CONTACTS -> return PermissionsName.WRITE_CONTACTS
                Manifest.permission.GET_ACCOUNTS -> return PermissionsName.GET_ACCOUNTS
                Manifest.permission.ACCESS_FINE_LOCATION -> return PermissionsName.ACCESS_FINE_LOCATION
                Manifest.permission.ACCESS_COARSE_LOCATION -> return PermissionsName.ACCESS_COARSE_LOCATION
                Manifest.permission.RECORD_AUDIO -> return PermissionsName.RECORD_AUDIO
                Manifest.permission.READ_PHONE_STATE -> return PermissionsName.READ_PHONE_STATE
                Manifest.permission.CALL_PHONE -> return PermissionsName.CALL_PHONE
                Manifest.permission.ADD_VOICEMAIL -> return PermissionsName.ADD_VOICEMAIL
                Manifest.permission.USE_SIP -> return PermissionsName.USE_SIP
                Manifest.permission.BODY_SENSORS -> return PermissionsName.BODY_SENSORS
                Manifest.permission.SEND_SMS -> return PermissionsName.SEND_SMS
                Manifest.permission.RECEIVE_SMS -> return PermissionsName.RECEIVE_SMS
                Manifest.permission.READ_SMS -> return PermissionsName.READ_SMS
                Manifest.permission.RECEIVE_WAP_PUSH -> return PermissionsName.RECEIVE_WAP_PUSH
                Manifest.permission.RECEIVE_MMS -> return PermissionsName.RECEIVE_MMS
                Manifest.permission.READ_EXTERNAL_STORAGE -> return PermissionsName.READ_EXTERNAL_STORAGE
                Manifest.permission.WRITE_EXTERNAL_STORAGE -> return PermissionsName.WRITE_EXTERNAL_STORAGE
                else -> return PermissionsName.UNKNOWN
            }
        }
    }
}