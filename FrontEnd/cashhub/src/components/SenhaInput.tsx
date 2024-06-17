// componentes/PasswordInput.tsx
import React from 'react';
import { View, Text, TextInput, StyleSheet } from 'react-native';

type Props = {
  password: string;
  setPassword: (password: string) => void;
};

export function PasswordInput({ password, setPassword }: Props) {
  return (
    <View style={styles.inputContainer}>
      <Text style={styles.inputLabel}>Senha</Text>
      <TextInput
        style={styles.input}
        value={password}
        onChangeText={setPassword}
        placeholder="Enter your password"
        secureTextEntry
      />
    </View>
  );
}

const styles = StyleSheet.create({
  inputContainer: {
    marginBottom: 20,
  },
  inputLabel: {
    fontSize: 16,
    marginBottom: 8,
  },
  input: {
    height: 40,
    borderColor: '#ccc',
    borderWidth: 1,
    paddingHorizontal: 10,
    borderRadius: 4,
  },
});
