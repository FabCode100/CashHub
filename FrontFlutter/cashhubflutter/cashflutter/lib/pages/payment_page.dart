import 'package:flutter/material.dart';

class PaymentScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Payment Screen'),
      ),
      body: Center(
        child: Text(
          'Bem-vindo à tela de pagamento!',
          style: TextStyle(fontSize: 24),
        ),
      ),
    );
  }
}
