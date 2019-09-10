import 'package:flutter/material.dart';
import 'dart:async';

import 'package:permissions_plugin/permissions_plugin.dart';


void main() => runApp(MyInitApp());

class MyInitApp extends StatelessWidget {
  // This widget is the root of your application.

  @override
  Widget build(BuildContext context) {
    //initPlatformState();
    return MaterialApp(
      title: 'Ecample permission',
      //showPerformanceOverlay: true,
      //showSemanticsDebugger: true,
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.green,
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => MyApp(),
        '/login': (context) => MyApp()
      },
      //home: MyApp(),
    );
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    checkPermissions(context);

    return Scaffold(
      appBar: AppBar(),
    );
  }

  Future<void> checkPermissions(BuildContext context) async {

    Map<Permission, PermissionState> permission = await PermissionsPlugin
        .checkPermissions([
      Permission.ACCESS_FINE_LOCATION,
      Permission.ACCESS_COARSE_LOCATION,
      Permission.READ_PHONE_STATE
    ]);

    if( permission[Permission.ACCESS_FINE_LOCATION] != PermissionState.GRANTED ||
        permission[Permission.ACCESS_COARSE_LOCATION] != PermissionState.GRANTED ||
        permission[Permission.READ_PHONE_STATE] != PermissionState.GRANTED ) {

      try {
        permission = await PermissionsPlugin
            .requestPermissions([
          Permission.ACCESS_FINE_LOCATION,
          Permission.ACCESS_COARSE_LOCATION,
          Permission.READ_PHONE_STATE
        ]);
      } on Exception {
        debugPrint("Error");
      }

      if( permission[Permission.ACCESS_FINE_LOCATION] == PermissionState.GRANTED &&
          permission[Permission.ACCESS_COARSE_LOCATION] == PermissionState.GRANTED &&
          permission[Permission.READ_PHONE_STATE] == PermissionState.GRANTED )
        print("Login ok");
      else
        permissionsDenied(context);

    } else {
      print("Login ok");
    }
  }

  void permissionsDenied(BuildContext context){
    showDialog(context: context, builder: (BuildContext _context) {
      return SimpleDialog(
        title: const Text("Permisos denegados"),
        children: <Widget>[
          Container(
            padding: EdgeInsets.only(left: 30, right: 30, top: 15, bottom: 15),
            child: const Text(
              "Debes conceder todo los permiso para poder usar esta aplicacion",
              style: TextStyle(
                  fontSize: 18,
                  color: Colors.black54
              ),
            ),
          )
        ],
      );
    });
  }
}

