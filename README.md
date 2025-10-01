# SmartPátio — Challenge Mottu

O **SmartPátio** é um sistema web, desenvolvido em Thymeleaf, Spring Boot e Spring Security, com o objetivo de otimizar a **gestão** das motos nos pátios da Mottu. A solução vem como uma integração ao sistema que a Mottu utiliza atualmente, automatizando etapas manuais e resolvendo a dor da localização rápida das motos.

## Contexto da Solução

- **Corredor inteligente:** câmera com visão computacional e OCR para leitura automática de placas na entrada/saída
- **Dispositivo IOT (carrapato):** dispositivo acoplado à moto, composto por ESP32, LED âmbar, buzzer audível e acoplamento magnético.
- **Sistema Web:** interface para registro de históricos (de entrada e saída), gestão das motos no pátio, integrada ao sistema da Mottu.


---

## 📖 Sobre o Projeto
O **SmartPatio** é uma aplicação web que permite:
- Registrar entrada e saída de motos em filiais.
- Vincular motos a *carrapatos* (dispositivos de rastreamento).
- Gerenciar usuários (Gestores e Funcionários).
- Exibir estatísticas em tempo real no dashboard.
- Acompanhar nível de bateria dos carrapatos.

---

## 🏗 Arquitetura
- **Camada Controller**: Responsável por receber as requisições HTTP.
- **Camada Service**: Contém as regras de negócio.
- **Camada Repository**: Comunicação com o banco via Spring Data JPA.
- **Camada DomainModel**: Entidades mapeadas com JPA.
- **DTOs**: Transferência de dados entre camadas.

Fluxo:
```
Controller -> Service -> Repository -> Database
```

---

## ⚙️ Funcionalidades
✅ Autenticação e autorização com Spring Security.  
✅ CRUD de usuários e perfil.  
✅ Registro de entrada/saída de motos.  
✅ Dashboard com:
   - Motos ativas e finalizadas.
   - Funcionários da filial.
   - Nível de bateria dos carrapatos.  

---

## 🛠 Tecnologias
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security**
- **Thymeleaf**
- **Hibernate**
- **Oracle**
- **Flyway** (migrations)
- **Lombok**

---

## 📋 Pré-requisitos
Antes de rodar o projeto, você precisa ter instalado:
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.8+](https://maven.apache.org/)
- Banco de dados (Oracle ou H2 em memória)

---

## ▶️ Instalação e Execução
1. Clone o repositório:
   ```bash
   git clone https://github.com/MariaEdPaixao/SmartPatio-Java.git
   cd smartpatio
   ```

2. Configure o banco no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
   spring.datasource.username=SEU_USER
   spring.datasource.password=SEU_PASS
   ```

3. Inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```

5. Acesse no navegador:
   ```
   http://localhost:8080
   ```
## 🐳 Executar via Docker

O sistema Web da SmartPatio desenvolvido em Java com Thymeleaf está disponível como imagem pública no Docker Hub: `mariaedpaixao/smartpatio-java`.

- Pré-requisito: ter o Docker instalado e acesso à base Oracle.
- A imagem escuta na porta interna 8080.

Passos:
1) Baixe a imagem
```
docker pull mariaedpaixao/smartpatio-java
```
2) Execute o container:
```
docker run -d -p 8080:8080 -e DB_USERNAME=$DB_USERNAME -e DB_PASSWORD=$DB_PASSWORD --name smartpatio mariaedpaixao/smartpatio-java:latest
```

Acesse o sistema web: http://localhost:8080

---

## 📂 Migrations
As migrations são gerenciadas pelo **Flyway**.  
Exemplos:
- `V2__create_tables.sql` → Criação das tabelas principais (`usuario`, `filial`, `moto`, etc.).
- `V3__insert_initial_data.sql` → Inserção de filiais, motos e carrapatos iniciais.
- `V4__comments_tables.sql` → Comentários nas tabelas para documentação.
- `V5__add_indexes.sql` → Índices para melhorar performance.

---

## 📊 Dashboard
O dashboard apresenta:
- 📌 Lista de motos **ativas no pátio** (com placa e carrapato vinculado).  
- 📌 Lista de motos que **já saíram** do pátio.  
- 📈 Gráfico de níveis de bateria dos carrapatos.  
- ➕ Atalho para **registrar entrada/saída** de motos.

---

## 🤝 Integrantes

| Nome                              | RM     | GitHub                                             |
| --------------------------------- | ------ | -------------------------------------------------- |
| **Laura de Oliveira Cintra**      | 558843 | [@Laura-Cintra](https://github.com/Laura-Cintra)   |
| **Maria Eduarda Alves da Paixão** | 558832 | [@MariaEdPaixao](https://github.com/MariaEdPaixao) |
| **Vinícius Saes de Souza**        | 554456 | [@ViniciuSaeSouza](https://github.com/ViniciuSaeSouza) |
