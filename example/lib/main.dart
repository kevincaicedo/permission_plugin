import 'package:flutter/material.dart';
import 'dart:async';

import 'package:image_picker/image_picker.dart';
// import 'package:imei_plugin/imei_plugin.dart';
import 'package:flutter/services.dart';
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

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  Map<Permission, PermissionState> _platformVersion;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    Map<Permission, PermissionState> platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      // String imei = await ImeiPlugin.getImei;
      var image = await ImagePicker.pickImage(source: ImageSource.gallery);
      platformVersion = await PermissionsPlugin.requestPermissions([
            Permission.ACCESS_FINE_LOCATION,
      ]);
    } on PlatformException {
      debugPrint("Error");
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  @override
  Widget build(BuildContext context) {
    return  Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on:'),
        ),
    );
  }
}
