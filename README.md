# Product Catalog Application

A Spring Boot REST API application for managing products with JWT-based authentication and role-based authorization.

## Technologies

- Java
- Spring Boot
- Spring Security
- JWT
- MySQL
- Spring Data JPA
- Maven

## Features

- User registration with roles
- User login
- JWT authentication
- Role-based authorization
- Product CRUD operations
- Soft delete
- Hard delete
- MySQL database integration
- API error handling

## Roles and Permissions

| Role | Permission |
|------|------------|
| ADMIN | Read and Write |
| USER | Read |
| OPERATOR | Write |

## Product Authorization

| Operation | Allowed Role |
|-----------|--------------|
| GET Product | ADMIN / USER / OPERATOR |
| POST Product | ADMIN |
| PUT Product | OPERATOR |
| DELETE Product | OPERATOR |

## Running the Application

### 1. Set the database password

Set the DB_PASSWORD environment variable with your MySQL password.

Example:

export DB_PASSWORD='your_mysql_password'

### 2. Start the application

Run:

mvn spring-boot:run

The application runs on:

http://localhost:8080

## Authentication APIs

### Register

POST /api/auth/register

Parameters:

- username
- password
- roleName

### Login

POST /api/auth/login

The login response provides a JWT token.

Use the token in authenticated API requests:

Authorization: Bearer <JWT_TOKEN>

## Product APIs

GET /api/products

POST /api/products

PUT /api/products/{id}

DELETE /api/products/{id}

## Delete Behavior

- softDeleted = true → Product is soft deleted.
- Otherwise → Product can be permanently deleted.

## Testing

The application was tested using Postman and cURL for:

- Successful authentication
- JWT token generation
- Role-based access
- Product creation
- Product retrieval
- Product update
- Soft delete
- Hard delete
- Unauthorized access returning 403 Forbidden
