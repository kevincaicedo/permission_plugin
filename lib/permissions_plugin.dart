import 'dart:async';

import 'package:flutter/services.dart';

enum PermissionGroup {
  CALENDAR,
  CALL_LOG,
  CAMERA,
  CONTACTS,
  LOCATION,
  MICROPHONE,
  PHONE,
  SENSORS,
  SMS,
  STORAGE
}

const Map<PermissionGroup, String> PermissionGroupName = {
  PermissionGroup.CALENDAR: "CALENDAR",
  PermissionGroup.CALL_LOG: "CALL_LOG",
  PermissionGroup.CAMERA: "CAMERA",
  PermissionGroup.CONTACTS: "CONTACTS",
  PermissionGroup.LOCATION: "LOCATION",
  PermissionGroup.MICROPHONE: "MICROPHONE",
  PermissionGroup.PHONE: "PHONE",
  PermissionGroup.SENSORS: "SENSORS",
  PermissionGroup.SMS: "SMS",
  PermissionGroup.STORAGE: "STORAGE",
};

enum PermissionResult {
  DENIED,
  GRANTED
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

  static Future<Map<PermissionGroup, PermissionResult>> requestPermissions(List<PermissionGroup> permissions) async {
    if(permissions.isEmpty) return null;

    final List<String> permissionsValues = permissions
        .map((PermissionGroup pg) => PermissionGroupName[pg])
        .cast<String>().toList();

    final Map<dynamic, dynamic> results = await _channel.invokeMethod(CommandName[0], permissionsValues);
    final Map<PermissionGroup, PermissionResult> permissionResult = mappingResult(Map<int, int>.from(results));
    return permissionResult;
  }

  static Future<Map<PermissionGroup, PermissionResult>> checkPermissions(List<PermissionGroup> permissions) async {
    if(permissions.isEmpty) return null;

    final List<String> permissionsValues = permissions
        .map((PermissionGroup pg) => PermissionGroupName[pg])
        .cast<String>().toList();

    final Map<dynamic, dynamic> results = await _channel.invokeMethod(CommandName[1], permissionsValues);
    final Map<PermissionGroup, PermissionResult> permissionResult = mappingResult(Map<int, int>.from(results));
    return permissionResult;
  }

  static Map<PermissionGroup, PermissionResult> mappingResult(Map<int, int> results){
    return results.map((int pg, int pr) => MapEntry(PermissionGroup.values[pg], PermissionResult.values[pr]));
  }

}
