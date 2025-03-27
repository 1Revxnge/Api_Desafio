# Cadastro de Clientes - API e Frontend

Este projeto é um sistema de cadastro de clientes com informações como nome, CPF, endereço, data de nascimento e contato. Ele é composto por uma API backend em Java com Spring Boot e um frontend simples em HTML, CSS e JavaScript. A API permite realizar operações de criação, leitura, atualização e exclusão (CRUD) de clientes, e a interface web oferece um formulário para interação com o sistema.

## Estrutura do Projeto

A estrutura do projeto é a seguinte:

/api

/controller

ApiController.java # Controlador principal para gerenciar as operações CRUD das tarefas

/repository

TaskRepository.java # Repositório para acessar os dados da base de dados

/model

Task.java # Modelo de dados (representação de um cliente)

/frontend

index.html # Página inicial com o formulário de cadastro de cliente

styles.css # Arquivo de estilos para a página de cadastro

script.js # Arquivo JavaScript para interação com a API

application.properties # Arquivo de configuração do Spring Boot

pom.xml # Dependências do Maven para o backend

php-template
Copiar
Editar

## Dependências

### Backend (Spring Boot)
- **Spring Web**: Para a criação de APIs RESTful.
- **Spring Data JPA**: Para comunicação com o banco de dados.
- **H2 Database** (opcional): Banco de dados em memória para armazenamento dos clientes (pode ser substituído por outro banco, como MySQL ou PostgreSQL).

Exemplo de dependência no `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
Frontend (HTML, CSS e JavaScript)
Nenhuma dependência externa além do JavaScript puro.

Configurações
Backend
O backend foi configurado com CORS para permitir requisições provenientes do endereço http://127.0.0.1:5500. A configuração está dentro do arquivo ApiController.java:

java
Copiar
Editar
@CrossOrigin(origins = "http://127.0.0.1:5500")
Se você for rodar o frontend em outro endereço, altere o valor de origins de acordo.

Banco de Dados
O backend usa o banco de dados H2 por padrão, configurado no arquivo application.properties:

properties
Copiar
Editar
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
Para usar outro banco, como MySQL ou PostgreSQL, modifique as configurações de conexão e as dependências no pom.xml.


Frontend (HTML, CSS, JavaScript)
Abra o diretório frontend e abra o arquivo index.html em um navegador.

O formulário será exibido, permitindo cadastrar novos clientes. As informações serão enviadas para a API através de requisições HTTP.

Funcionalidades
Cadastro de Clientes: O formulário de cadastro permite adicionar clientes com as informações de nome, CPF, endereço, data de nascimento e contato.

Exibição de Clientes Cadastrados: A lista de clientes cadastrados será exibida abaixo do formulário.

Edição e Exclusão de Clientes: Para editar ou excluir um cliente, clique no botão correspondente ao lado de cada cliente na lista.

Rotas da API
GET /tasks: Retorna todos os clientes cadastrados.

POST /tasks: Adiciona um novo cliente.

DELETE /tasks: Limpa todos os clientes cadastrados.

DELETE /tasks/{id}: Deleta um cliente pelo ID.

PATCH /tasks/{id}: Atualiza as informações de um cliente específico.

Como Contribuir
Faça o fork do repositório.

Crie uma nova branch para suas alterações (git checkout -b nome-da-sua-branch).

Realize suas alterações e faça o commit (git commit -am 'Adiciona nova funcionalidade').

Envie suas alterações para o repositório (git push origin nome-da-sua-branch).

Crie um pull request.