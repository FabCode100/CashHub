import 'package:flutter/material.dart';
import 'package:cashflutter/pages/login_page.dart'; // Importe a tela LoginPage

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Login App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: LoginPage(), // Defina LoginPage como a tela inicial
    );
  }
}
