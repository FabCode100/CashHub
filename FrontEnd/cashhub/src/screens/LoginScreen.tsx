import React from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet } from 'react-native';

const LoginScreen: React.FC = () => {
  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.logo}>CASH HUB</Text>
      </View>
      <View style={styles.body}>
        <TextInput
          style={styles.input}
          placeholder="Email"
          keyboardType="email-address"
        />
        <TextInput
          style={styles.input}
          placeholder="Senha"
          secureTextEntry
        />
        <TouchableOpacity style={styles.button}>
          <Text style={styles.buttonText}>Login</Text>
        </TouchableOpacity>
        <Text style={styles.forgotPassword}>Esqueceu a senha?</Text>
        <Text style={styles.signup}>
          Não tem uma conta? <Text style={styles.signupLink}>Faça o cadastro!</Text>
        </Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  header: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#e65c22',
    borderBottomLeftRadius: 40,
    borderBottomRightRadius: 40,
  },
  logo: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#fff',
  },
  body: {
    flex: 2,
    paddingHorizontal: 20,
    justifyContent: 'center',
  },
  input: {
    height: 50,
    borderColor: '#e65c22',
    borderWidth: 1,
    borderRadius: 25,
    paddingHorizontal: 20,
    marginVertical: 10,
  },
  button: {
    height: 50,
    backgroundColor: '#e65c22',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 25,
    marginVertical: 10,
  },
  buttonText: {
    color: '#fff',
    fontSize: 18,
  },
  forgotPassword: {
    textAlign: 'center',
    color: '#666',
    marginVertical: 10,
  },
  signup: {
    textAlign: 'center',
    color: '#666',
    marginVertical: 10,
  },
  signupLink: {
    color: 'purple',
    fontWeight: 'bold',
  },
});

export default LoginScreen;
