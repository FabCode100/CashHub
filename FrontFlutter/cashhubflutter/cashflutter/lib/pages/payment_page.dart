import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:cashflutter/pages/success_page.dart';

class PaymentScreen extends StatefulWidget {
  const PaymentScreen({super.key});

  @override
  _PaymentScreenState createState() => _PaymentScreenState();
}

class _PaymentScreenState extends State<PaymentScreen> {
  final _formKey = GlobalKey<FormState>();
  final TextEditingController _cardNumberController = TextEditingController();
  final TextEditingController _expiryDateController = TextEditingController();
  final TextEditingController _cvvController = TextEditingController();
  final TextEditingController _amountController = TextEditingController();
  String? _cardType;

  Future<void> _processPayment() async {
    const String apiUrl =
        'http://localhost:8080/api/payments/process'; // Substitua pela sua URL de backend
    final String token;
    // Seleciona o token com base no tipo do cartão
    switch (_cardType) {
      case 'Visa':
        token = 'tok_visa';
        break;
      case 'MasterCard':
        token = 'tok_mastercard';
        break;
      case 'American Express':
        token = 'tok_amex';
        break;
      default:
        token = '';
    }
    final Map<String, dynamic> paymentData = {
      'cardNumber': _cardNumberController.text,
      'expirationDate': _expiryDateController.text,
      'cvv': _cvvController.text,
      'amount': _amountController.text,
      'cardType': _cardType,
      'token': token
    };
    print(paymentData);
    try {
      final String basicAuth =
          'Basic ${base64Encode(utf8.encode('admin:adminpassword'))}';

      final response = await http.post(
        Uri.parse(apiUrl),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': basicAuth,
        },
        body: jsonEncode(paymentData),
      );
      print(jsonEncode(paymentData));
      print('Response status: ${response.statusCode}');
      print('Response body: ${response.body}');

      if (response.statusCode == 200) {
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => const SuccessScreen()),
        );
      } else {
        _showDialog(context, 'Payment Failed',
            'Server error: ${response.statusCode}\n${response.body}');
      }
    } catch (e) {
      print('Error occurred: $e');
      _showDialog(context, 'Payment Failed', 'An error occurred: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Payment Screen',
          style: TextStyle(color: Colors.white),
        ),
        backgroundColor: Colors.orange,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            children: <Widget>[
              _buildCardNumberField(),
              const SizedBox(height: 16),
              _buildExpiryDateField(),
              const SizedBox(height: 16),
              _buildCVVField(),
              const SizedBox(height: 16),
              _buildAmountField(),
              const SizedBox(height: 16),
              _buildCardTypeDropdown(),
              const SizedBox(height: 30),
              _buildPayButton(context),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildCardNumberField() {
    return TextFormField(
      controller: _cardNumberController,
      decoration: InputDecoration(
        labelText: 'Card Number',
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        prefixIcon: const Icon(Icons.credit_card),
        filled: true,
        fillColor: Colors.grey[200],
      ),
      keyboardType: TextInputType.number,
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Please enter your card number';
        }
        if (value.length != 16) {
          return 'Card number must be 16 digits';
        }
        return null;
      },
    );
  }

  Widget _buildExpiryDateField() {
    return TextFormField(
      controller: _expiryDateController,
      decoration: InputDecoration(
        labelText: 'Expiry Date (MM/YY)',
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        prefixIcon: const Icon(Icons.date_range),
        filled: true,
        fillColor: Colors.grey[200],
      ),
      keyboardType: TextInputType.datetime,
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Please enter the expiry date';
        }
        if (!RegExp(r'^(0[1-9]|1[0-2])\/?([0-9]{2})$').hasMatch(value)) {
          return 'Invalid expiry date';
        }
        return null;
      },
    );
  }

  Widget _buildCVVField() {
    return TextFormField(
      controller: _cvvController,
      decoration: InputDecoration(
        labelText: 'CVV',
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        prefixIcon: const Icon(Icons.lock),
        filled: true,
        fillColor: Colors.grey[200],
      ),
      keyboardType: TextInputType.number,
      obscureText: true,
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Please enter the CVV';
        }
        if (value.length != 3) {
          return 'CVV must be 3 digits';
        }
        return null;
      },
    );
  }

  Widget _buildAmountField() {
    return TextFormField(
      controller: _amountController,
      decoration: InputDecoration(
        labelText: 'Amount',
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        prefixIcon: const Icon(Icons.attach_money),
        filled: true,
        fillColor: Colors.grey[200],
      ),
      keyboardType: TextInputType.number,
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Please enter the amount';
        }
        if (double.tryParse(value) == null) {
          return 'Invalid amount';
        }
        return null;
      },
    );
  }

  Widget _buildCardTypeDropdown() {
    return DropdownButtonFormField<String>(
      value: _cardType,
      decoration: InputDecoration(
        labelText: 'Card Type',
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        prefixIcon: const Icon(Icons.credit_card),
        filled: true,
        fillColor: Colors.grey[200],
      ),
      items: ['Visa', 'MasterCard', 'American Express']
          .map((label) => DropdownMenuItem(
                value: label,
                child: Text(label),
              ))
          .toList(),
      onChanged: (value) {
        setState(() {
          _cardType = value;
        });
      },
      validator: (value) {
        if (value == null) {
          return 'Please select a card type';
        }
        return null;
      },
    );
  }

  Widget _buildPayButton(BuildContext context) {
    return ElevatedButton(
      onPressed: () {
        if (_formKey.currentState!.validate()) {
          _processPayment();
        }
      },
      style: ElevatedButton.styleFrom(
        backgroundColor: Colors.orange,
        padding: const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
        textStyle: const TextStyle(fontSize: 18),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10),
        ),
      ),
      child: const Text(
        'Pay',
        style:
            TextStyle(color: Colors.white), // Define a cor do texto como branco
      ),
    );
  }

  void _showDialog(BuildContext context, String title, String message) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(title),
          content: Text(message),
          actions: <Widget>[
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: const Text('OK'),
            ),
          ],
        );
      },
    );
  }
}
