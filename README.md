# Employee Management System

## Overview

This Employee Management System is a Spring Boot application that provides CRUD operations for managing employee records. The application is containerized using Docker, enabling easy deployment and scalability.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete employee records.
- **Spring Boot**: Java-based framework for building the application.
- **Docker**: Containerization of the application for consistent deployment.

## Prerequisites

- **Docker**: Ensure Docker is installed on your machine.
- **Java**: Java 17 is recommended for building and running the application.

## Getting Started

### Cloning the Repository

```bash
  git clone https://github.com/AnushkaHere/EmployeeManagementWithDocker.git
```
```bash
  cd EmployeeManagementWithDocker
```
### Building the Docker Image
Navigate to the project directory where the Dockerfile is located.

Build the Docker image using the following command:

```bash
  docker build -t employee-management-system .
```

### Running the Docker Container
Run the Docker container using the following command:

```bash
  docker run -d -p 8080:8080 --name employee-management-system employee-management-system
```
- `d`: Run the container in detached mode.
- `p 8080:8080`: Map port 8080 on the host to port 8080 on the container.
- `name EmployeeManagementWithDocker` : Assign a name to the container.

### Accessing the Application
Once the container is running, you can access the application at:

**Base URL**: http://localhost:8080/api/employees

### API Endpoints
- **GET** */api/employees* - Retrieve all employees.
- **GET** */api/employees/{id}* - Retrieve an employee by ID.
- **POST** */api/employees* - Add a new employee.
- **PUT** */api/employees/{id}* - Update an existing employee.
- **DELETE** */api/employees/{id}* - Delete an employee by ID.

### Sample Request and Response

#### Create Employee
**Endpoint**: *POST* `/api/employees`

**Request Body**:

```json
  {
      "name": "John Doe",
      "email": "john.doe@example.com",
      "department": "Engineering",
      "salary": 60000
  }
```
**Response**:

```json
  {
      "id": 1,
      "name": "John Doe",
      "email": "john.doe@example.com",
      "department": "Engineering",
      "salary": 60000
  }
```

#### Update Employee
**Endpoint**: *PUT* `/api/employees/{id}`

**Request Body**:

```json
  {
      "name": "Jane Doe",
      "email": "jane.doe@example.com",
      "department": "Engineering",
      "salary": 65000
  }
```
**Response**:

```json
  {
      "id": 1,
      "name": "Jane Doe",
      "email": "jane.doe@example.com",
      "department": "Engineering",
      "salary": 65000
  }
```

### Stopping and Removing the Container
To stop the running container:

```bash
  docker stop employee-management-system
```

To remove the container:

```bash
  docker rm employee-management-system
```

### Troubleshooting
- **Error**: Port 8080 is already in use: Ensure no other application is using port 8080 or map to a different port using -p <host-port>:8080.
- **Error** : Application failed to start: Check the Docker build logs for issues related to dependencies or configuration.
