# Permissions Plugin

[ ![Build version](https://img.shields.io/badge/pub-v1.1.4-green)](https://pub.dev/packages/permissions_plugin)

Plugin for permission managment on runtime.

## Getting Started

### Installing

##### Use this package as a library

**1. Depend on it**
Add this to your package's pubspec.yaml file:
```dart
dependencies:
  permissions_plugin: ^1.1.4
```

**2. Install it**
You can install packages from the command line:

with Flutter:
```sh
$  flutter pub get
```
Alternatively, your editor might support flutter pub get. Check the docs for your editor to learn more.

**3. Import it**
Now in your Dart code, you can use:
```sh
import 'package:permissions_plugin/permissions_plugin.dart';
```

### New Features!

- Request ignore battery optimization
- check status ignore battery optimization

You can also:
- Request permission on runtime
- Check status permission on runtime


##### Request permission
```dart
import 'package:permissions_plugin/permissions_plugin.dart';

Map<Permission, PermissionState> permission = await PermissionsPlugin
    .requestPermissions([
        Permission.ACCESS_FINE_LOCATION,
        Permission.ACCESS_COARSE_LOCATION,
        Permission.READ_PHONE_STATE
    ]);
```

##### Check status permission
```dart
import 'package:permissions_plugin/permissions_plugin.dart';

Map<Permission, PermissionState> permission = await PermissionsPlugin
    .checkPermissions([
      Permission.ACCESS_FINE_LOCATION,
      Permission.ACCESS_COARSE_LOCATION,
      Permission.READ_PHONE_STATE
    ]);
```

##### Request ignore battery optimization
```dart
import 'package:permissions_plugin/permissions_plugin.dart';

final PermissionState resBattery = await PermissionsPlugin
    .requestIgnoreBatteryOptimization;
```

##### Check status ignore battery optimization
```dart
import 'package:permissions_plugin/permissions_plugin.dart';

final PermissionState status = await PermissionsPlugin
    .isIgnoreBatteryOptimization;
```

### List Permissions
Current suport [Android](https://developer.android.com/) only

```dart
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
  CALL_PHONE,
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
```

### List Permissions state
```dart
enum PermissionState {
  GRANTED,
  DENIED,
  UNKNOWN
}
```


---

### Platform Support
| OS | Min Version |
| -- | ----------- |
| Android | minSdkVersion 16 |
| IOS | **soon** |


License
----

MIT

## AUTHOR 

This plugin is developed, **Free Software, by Kevin Caicedo**