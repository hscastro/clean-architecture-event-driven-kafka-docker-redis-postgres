Este projeto demonstra uma arquitetura moderna baseada em **microserviços**, utilizando **Clean Architecture** e comunicação **Event-Driven** com Apache Kafka.

---

### 📌 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Apache Kafka
- PostgreSQL
- Redis (cache)
- Docker / Docker Compose
- Testcontainers (testes integrados)

---

###  🧱 Arquitetura Geral

A aplicação segue uma abordagem de microserviços com separação clara de responsabilidades e comunicação assíncrona baseada em eventos.


###  🧱 Camadas da Arquitetura

### 🎯 Application
- UseCases
- Orquestra regras de negócio

### 🧠 Domain
- Models
- Enums
- Repositories (interfaces)

### ⚙️ Infrastructure
- Kafka (producer/consumer)
- PostgreSQL
- Redis
- Configurações

### 🌐 Interfaces
- Controllers (REST)
- DTOs (Request/Response)
- Mapper
- Exception Handler
---

###  🧭 Desenho da Arquitetura

🧠 Clean Architecture (por serviço)

Cada microserviço segue a estrutura:

###  📁 Estrutura do Projeto

```

application/
  └── usecases/

domain/
  ├── enums/
  ├── model/
  └── repository/

infrastructure/
  ├── config/
  ├── messaging/
  │   ├── consumer/
  │   └── producer/
  ├── persistence/
  └── cache/

interfaces/
  ├── controllers/
  ├── dto/
  │   ├── request/
  │   └── response/
  ├── exception/
  ├── mapper/
  └── util/

```


### 🔑 Princípios aplicados:

Regra de dependência (de fora para dentro ❌ / de dentro para fora ✅)
Isolamento da regra de negócio
Independência de frameworks
⚡ Event-Driven Architecture

A comunicação entre microserviços ocorre via eventos utilizando Apache Kafka.

### 🔄 Fluxo:

Um pedido é criado no order-service
O serviço publica um evento ORDER_CREATED
Outros serviços consomem o evento
Cada serviço reage de forma independente

### 📦 Microserviço: order-service

Responsável por gerenciar pedidos.

### 📥 Request
```
{
  "product": "Oculos de grau",
  "quantity": 3,
  "price": 256.99,
  "status": "PENDING"
}
```

### 📤 Response
```
{
  "id": "c03975e6-687a-4bb8-9ad9-f1d9e4cf1cfa",
  "product": "Oculos de grau",
  "quantity": 3,
  "price": 256.99,
  "status": "PENDING"
}
```
### 🗄️ Persistência
PostgreSQL: armazenamento principal dos pedidos
Redis: cache para otimização de leitura

### 📡 Mensageria
Apache Kafka
Tópico exemplo: order-created-topic

Evento publicado:
```
{
  "eventType": "ORDER_CREATED",
  "data": {
    "id": "c03975e6-687a-4bb8-9ad9-f1d9e4cf1cfa",
    "product": "Oculos de grau",
    "quantity": 3,
    "price": 256.99,
    "status": "PENDING"
  }
}
```
### 🐳 Docker

Subir toda a infraestrutura:

docker-compose up -d

Serviços esperados:

Kafka
Zookeeper
PostgreSQL
Redis

### 🧪 Testes

Testes de integração com Testcontainers, garantindo execução com ambientes reais:

PostgreSQL containerizado
Kafka containerizado
./mvnw test

### 📈 Benefícios da Arquitetura

🔹 Baixo acoplamento
🔹 Alta escalabilidade
🔹 Resiliência (event-driven)
🔹 Facilidade de manutenção
🔹 Independência de deploy

Logs centralizados

### 📚 Referências
Clean Architecture — Robert C. Martin
Microservices Patterns — Chris Richardson
Event-Driven Architecture — Martin Fowler

### 🤝 Contribuição
Contribuições são bem-vindas!
