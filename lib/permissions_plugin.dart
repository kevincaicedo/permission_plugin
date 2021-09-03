import 'dart:async';

import 'package:flutter/services.dart';

enum Permission {
  READ_CALENDAR,
  WRITE_CALENDAR,
  READ_CALL_LOG,
  WRITE_CALL_LOG,
  PROCESS_OUTGOING_CALLS,
  CAMERA,
  READ_CONTACTS,
  WRITE_CONTACTS,
  GET_ACCOUNTS,
  ACCESS_FINE_LOCATION,
  ACCESS_COARSE_LOCATION,
  RECORD_AUDIO,
  READ_PHONE_STATE,
  READ_PHONE_NUMBERS,
  CALL_PHONE,
  ANSWER_PHONE_CALLS,
  ADD_VOICEMAIL,
  USE_SIP,
  BODY_SENSORS,
  SEND_SMS,
  RECEIVE_SMS,
  READ_SMS,
  RECEIVE_WAP_PUSH,
  RECEIVE_MMS,
  READ_EXTERNAL_STORAGE,
  WRITE_EXTERNAL_STORAGE
}

enum PermissionState {
  GRANTED,
  DENIED
}

class PermissionsPlugin {

  static const MethodChannel _channel =
      const MethodChannel('permissions_plugin');

  static const List<String> CommandName = [
    "request-permissions", "check-permissions"
  ];

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<Map<Permission, PermissionState>> requestPermissions(List<Permission> permissions) async {
    if(permissions.isEmpty) return null;

    final List<String> permissionsValues = permissions
        .map((Permission pg) => pg.toString().split(".")[1])
        .cast<String>().toList();

    final Map<dynamic, dynamic> results = await _channel.invokeMethod(CommandName[0], permissionsValues);
    final Map<Permission, PermissionState> permissionResult = mappingResult(Map<int, int>.from(results));
    return permissionResult;
  }

  static Future<Map<Permission, PermissionState>> checkPermissions(List<Permission> permissions) async {
    if(permissions.isEmpty) return null;

    final List<String> permissionsValues = permissions
        .map((Permission pg) => pg.toString().split(".")[1])
        .cast<String>().toList();

    final Map<dynamic, dynamic> results = await _channel.invokeMethod(CommandName[1], permissionsValues);
    final Map<Permission, PermissionState> permissionResult = mappingResult(Map<int, int>.from(results));
    return permissionResult;
  }

  static Map<Permission, PermissionState> mappingResult(Map<int, int> results){
    return results.map((int pg, int pr) => MapEntry(Permission.values[pg], PermissionState.values[pr]));
  }

}
