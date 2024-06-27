# CashHub App

## Pré-requisitos

Para rodar este projeto, você precisará ter instalado:

- Flutter 3.22
- Java SDK 21
- Maven 3.9.8

## Configuração do Stripe

Para testar a integração com o Stripe:

1. Configure sua chave secreta do Stripe como uma variável de sistema:
   ```
   export STRIPE_API_KEY=sua_secret_key_aqui
   ```

2. Adicione sua chave secreta do Stripe no arquivo `application.yaml` do backend.

## Rodando o Frontend

1. Clone o projeto:
   ```
   git clone https://github.com/FabCode100/CashHub
   cd FrontFlutter\cashhubflutter\cashflutter
   ```

2. Execute o aplicativo Flutter no navegador Chrome com segurança desabilitada:
   ```
   flutter run -d chrome --web-browser-flag "--disable-web-security"
   ```

3. Aguarde até que a página do Google Chrome seja aberta com o aplicativo.

## Rodando o Backend

1. Abra o arquivo `CashHubApplication.java` no seu IDE.

2. Execute a aplicação para iniciar o backend.

## Testando o Aplicativo

1. No navegador, acesse a página onde o frontend está sendo executado.

2. Faça login e insira informações fictícias de pagamento (número de cartão com 16 dígitos, data de expiração válida, etc.).

3. Clique em "Pagar". Se tudo estiver configurado corretamente, você verá uma mensagem de "Pagamento com sucesso" e o saldo da conta será atualizado.
