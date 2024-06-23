export const buscarClientSecretPagamento = async (amount: number): Promise<string> => {
    const response = await fetch('http://localhost:8080/api/payments/create-payment-intent', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ amount }),
    });
    const { clientSecret } = await response.json();
    return clientSecret;
  };
  