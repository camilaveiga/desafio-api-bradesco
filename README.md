# Desafio Cadastro de Usuario Bradesco </h2>

Desafio Backend - Desenvolver a API de backend do sistema TechManage, uma aprlicação para gerenciar usuários. O objetivo é criar uma API RESTful utilizando Spring Boot que permita realizar operações básicas de gerenciamento de usuários, conectando a aplicação a um banco de dados relacional.

## Como rodar

1. Clone o repositório;

```bash
$ git clone https://github.com/camilaveiga/desafio-api-bradesco.git
```

2. Instale e configure o PostgreSQL

Configure conforme o arquivo application.properties:

```yaml
CREATE DATABASE techmanage;
```

```yaml
spring.datasource.url=jdbc:postgresql://localhost:5432/techmanage
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

3. Execute a aplicação

## API Endpoints

A API oferece os seguintes endpoints:

POST /api/users: Adiciona um novo usuário

```json
{
	"fullName": "Camila Veiga",
	"email": "camilaveiga@gmail.com",
	"phone": "+55 11 9876-54321",
	"birthDate": "2002-04-05",
	"userType": "ADMIN"
}
```

GET /api/users: Lista todos os usuários

```json
[
	{
		"id": 1,
		"fullName": "Camila Camargo",
		"email": "camilaveiga@gmail.com",
		"phone": "+55 11 94127-7110",
		"birthDate": "2002-04-05T00:00:00.000+00:00",
		"userType": "ADMIN"
	},
	{
		"id": 3,
		"fullName": "Ana Maria",
		"email": "anamaria@gmail",
		"phone": "+55 11 98765-4321",
		"birthDate": "1976-11-08T00:00:00.000+00:00",
		"userType": "VIEWER"
	}
]
```

GET /api/users/{id}: Busca um usuário pelo ID

ex: http:localhost:8080/api/users/1

```json
{
	"fullName": "Camila Veiga",
	"email": "camilaveiga@gmail.com",
	"phone": "+55 11 9876-54321",
	"birthDate": "2002-04-05",
	"userType": "ADMIN"
}
```
PUT /api/users/{id}: Atualiza os dados de um usuário

ex: http:localhost:8080/api/users/1

```json
{
	"fullName": "Camila Batista Veiga",
	"email": "camilaveiga@gmail.com",
	"phone": "+55 11 9876-54321",
	"birthDate": "2002-04-05",
	"userType": "ADMIN"
}
```

DELETE /api/users/{id}: Deleta um usuário





