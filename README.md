# Blog Platform API

This Blog Platform API is built using Spring Boot and provides functionalities to manage blog posts, user authentication, comments. The API supports various CRUD operations, ensuring a robust platform for blog management.

## Features

- **User Management**: 
  - User registration and login
  - Password management

- **Blog Posts**:
  - Create, read, update, and delete blog posts

- **Comments**:
  - Add, edit, and delete comments on blog posts


- **Security**:
  - JWT-based authentication
  - Role-based access control

## Endpoints

### User Management

- **Register**: `POST /api/auth/register`
- **Login**: `POST /api/auth/login`
- **Change Password**: `POST /api/auth/change-password`

### Blog Posts

- **Get All Posts**
- **Get Single Post**
- **Create Post**
- **Update Post**
- **Delete Post**

### Comments

- **Get Comments for Post**
- **Add Comment**
- **Update Comment**
- **Delete Comment**


## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- MySQL or any preferred relational database

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/blog-platform-api.git
