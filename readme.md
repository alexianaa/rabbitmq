# Curso RabbitMQ

Projeto de estudo com Spring Boot, RabbitMQ, Docker e dois microsservicos:

- `producer-service`: recebe requisicoes HTTP e publica mensagens.
- `consumer-service`: recebe mensagens da fila e registra o conteudo.
- `commons`: compartilha o `ProductDTO` e as constantes do RabbitMQ.

## Fluxo

O producer publica mensagens na exchange direta `marketplace.direct` usando a routing key `product.log`. A exchange envia os itens para a fila `product.log`, consumida pelos listeners de texto e produto.

## Pre-requisitos

- Java 17
- Docker Desktop
- Windows PowerShell

## 1. Iniciar o RabbitMQ

Na raiz do projeto:

```powershell
docker compose up -d
```

Painel administrativo: http://localhost:15672/

- Usuario: `guest`
- Senha: `guest`

Para encerrar:

```powershell
docker compose down
```

## 2. Gerar o pacote commons

Os dois aplicativos dependem do JAR gerado pelo modulo `commons`:

```powershell
cd commons
.\mvnw.cmd clean install
cd ..
```

## 3. Iniciar o producer

```powershell
cd producer-service
.\gradlew.bat bootRun
```

O producer inicia na porta `8080`.

## 4. Iniciar o consumer

Em outro terminal:

```powershell
cd consumer-service
.\gradlew.bat bootRun
```

O consumer inicia na porta `8082`.

## Funcionalidades e endpoints

### Enviar uma mensagem de texto

```powershell
Invoke-RestMethod "http://localhost:8080/produces?message=Hello"
```

Corresponde ao endpoint:

```text
GET /produces?message={texto}
```

Resposta esperada:

```text
Sending message
```

### Enviar um produto

```powershell
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/products" -ContentType "application/json" -Body '{"id":1,"name":"Notebook","price":3500.0}'
```

Corresponde ao endpoint:

```text
POST /products
Content-Type: application/json
```

Exemplo de JSON:

```json
{
  "id": 1,
  "name": "Notebook",
  "price": 3500.0
}
```

O produto enviado sera publicado com Java serialization. O consumer permite explicitamente a classe `dtos.ProductDTO` durante a deserializacao, sem habilitar deserializacao irrestrita.

## Executar testes

Producer:

```powershell
cd producer-service
.\gradlew.bat test
```

Consumer:

```powershell
cd consumer-service
.\gradlew.bat test
```

Commons:

```powershell
cd commons
.\mvnw.cmd test
```

## Comandos uteis

Ver containers em execucao:

```powershell
docker compose ps
```

Ver logs do RabbitMQ:

```powershell
docker compose logs -f rabbitmq
```
