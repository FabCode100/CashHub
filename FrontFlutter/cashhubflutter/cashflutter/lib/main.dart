import 'package:flutter/material.dart';
import 'package:cashflutter/pages/login_page.dart'; // Importe a tela LoginPage
import 'package:flutter_stripe_web/flutter_stripe_web.dart';

void main(){
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Login App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: LoginPage(), // Defina LoginPage como a tela inicial
    );
  }
}
