// componentes/EmailInput.tsx
import React from 'react';
import { View, Text, TextInput, StyleSheet } from 'react-native';

type Props = {
  email: string;
  setEmail: (email: string) => void;
};

export function EmailInput({ email, setEmail }: Props) {
  return (
    <View style={styles.inputContainer}>
      <Text style={styles.inputLabel}>Email</Text>
      <TextInput
        style={styles.input}
        value={email}
        onChangeText={setEmail}
        placeholder="Enter your email"
        autoCapitalize="none"
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
