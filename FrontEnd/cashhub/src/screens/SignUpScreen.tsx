// src/screens/SignUpScreen.tsx
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const SignUpScreen: React.FC = () => {
  return (
    <View style={styles.container}>
      <Text>Cadastro</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default SignUpScreen;
