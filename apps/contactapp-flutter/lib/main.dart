import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:contactappflutter/addEntry.dart';
import 'package:contactappflutter/home.dart';

void main() {

  runApp(MaterialApp(
    title: 'Contact App Flutter',
    initialRoute: '/',
    routes: {
      '/': (context) => Home(),
      '/home': (context) => Home(),
      '/addEntry': (context) => AddEntry(),
    },
  ));
}