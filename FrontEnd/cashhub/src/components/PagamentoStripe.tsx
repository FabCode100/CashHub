import React, { useState } from 'react';
import { StyleSheet, View, Button, Alert } from 'react-native';
import { CardField, useConfirmPayment, StripeProvider, CardFieldInput } from '@stripe/stripe-react-native';
import { buscarClientSecretPagamento } from '../services/stripeService';

const PagamentoStripe = () => {
  const [detalhesCartao, setDetalhesCartao] = useState<CardFieldInput.Details | null>(null);
  const { confirmPayment } = useConfirmPayment();

  const handlePagarPress = async () => {
    const clientSecret = await buscarClientSecretPagamento(10050);

    const { error, paymentIntent } = await confirmPayment(clientSecret, {
      paymentMethodType: 'Card',
      paymentMethodData: {
        billingDetails: {
          email: 'email@exemplo.com',
        },
      },
    });

    if (error) {
      Alert.alert(`Pagamento falhou: ${error.message}`);
    } else if (paymentIntent) {
      Alert.alert('Pagamento bem-sucedido!', `ID do PaymentIntent: ${paymentIntent.id}`);
    }
  };

  return (
    <StripeProvider publishableKey="pk_test_TUACHAVEDETESTEAQUI">
      <View style={estilos.container}>
        <CardField
          postalCodeEnabled={false}
          placeholders={{
            number: '4242 4242 4242 4242',
          }}
          cardStyle={estilos.cartao}
          style={estilos.containerCartao}
          onCardChange={(detalhesCartao) => {
            setDetalhesCartao(detalhesCartao.complete ? detalhesCartao : null);
          }}
        />
        <Button onPress={handlePagarPress} title="Pagar" disabled={!detalhesCartao?.complete} />
      </View>
    </StripeProvider>
  );
};

const estilos = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 16,
  },
  cartao: {
    backgroundColor: '#FFFFFF',
  },
  containerCartao: {
    height: 50,
    marginVertical: 30,
  },
});

export default PagamentoStripe;
