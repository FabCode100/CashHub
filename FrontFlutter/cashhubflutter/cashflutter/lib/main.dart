import 'package:flutter/material.dart';
import 'package:cashflutter/pages/login_page.dart'; // Importe a tela LoginPage
import 'package:flutter_stripe/flutter_stripe.dart';

void main() async {
  Stripe.publishableKey =
      "pk_test_51PPDUwB6zFwZkiVemssgUuTeVNYEo3Q8PcCNA4xROl3eabzl134O6Q8SZnOy6aauNaxSTMxBX6qJLTT0XCP5M3zi00N1YmO1Oq";
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
