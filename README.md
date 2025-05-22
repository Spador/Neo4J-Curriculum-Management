# Course Enrollment System - Backend

This is the backend service for a Course Enrollment System built with Spring Boot and Neo4j. The system provides a robust API for managing courses, users, and enrollments in a graph database environment.

## 🚀 Features

- **User Management**
  - User registration and authentication
  - Role-based access control
  - Secure password handling with BCrypt encryption

- **Course Management**
  - Create and manage courses
  - Course details with lessons
  - Course enrollment tracking

- **Enrollment System**
  - Enroll users in courses
  - Track user enrollments
  - View enrolled courses

## 🛠️ Technology Stack

- **Backend Framework**: Spring Boot 3.3.4
- **Database**: Neo4j (Graph Database)
- **Language**: Java 21
- **Security**: Spring Security
- **API Documentation**: Spring Boot Actuator
- **Environment Management**: Spring Dotenv

## 📋 Prerequisites

- Java 21 or higher
- Maven
- Neo4j Database (Aura or local instance)
- IDE (IntelliJ IDEA recommended)

## 🔧 Configuration

1. Create a `.env` file in the root directory with the following variables:
```env
AURA_URI=your_neo4j_uri
AURA_USER=your_neo4j_username
AURA_PASSWORD=your_neo4j_password
```

2. The application will automatically load these environment variables using the spring-dotenv dependency.

## 🏗️ Project Structure

```
src/main/java/dev/shivam/spring_boot_neo4j/
├── config/                 # Configuration classes
├── controllers/           # REST API endpoints
├── models/               # Neo4j entity models
├── object/              # DTOs for data transfer
├── repositories/        # Neo4j repositories
├── requests/           # Request objects
├── services/          # Business logic
└── SpringBootNeo4jApplication.java
```

## 🚀 Getting Started

1. **Clone the repository**
```bash
git clone <repository-url>
cd spring-boot-neo4j
```

2. **Build the project**
```bash
./mvnw clean install
```

3. **Run the application**
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Authentication Endpoints

- `POST /api/v1/auth/register`
  - Register a new user
  - Request body:
    ```json
    {
      "name": "User Name",
      "username": "username",
      "password": "password",
      "roles": "ROLE_USER"
    }
    ```

- `GET /api/v1/auth/me`
  - Get current user information
  - Requires authentication

### Course Endpoints

- `GET /api/v1/courses`
  - List all available courses
  - Optional authentication for enrollment status

- `GET /api/v1/courses/{identifier}`
  - Get detailed information about a specific course
  - Returns course details with lessons

### Enrollment Endpoints

- `GET /api/v1/enrolments`
  - List all courses enrolled by the authenticated user
  - Requires authentication

- `POST /api/v1/enrolments`
  - Enroll in a course
  - Request body:
    ```json
    {
      "courseIdentifier": "course-id"
    }
    ```
  - Requires authentication

## 🔒 Security

The application uses Spring Security with the following features:
- Basic authentication
- Stateless session management
- CORS configuration
- Role-based access control
- Password encryption using BCrypt

## 🗄️ Database Schema

The Neo4j graph database uses the following structure:

- **Nodes**:
  - User
  - Course
  - Lesson

- **Relationships**:
  - (User)-[:ENROLLED_IN]->(Course)
  - (Lesson)-[:BELONGS_TO]->(Course)

## 🤝 Frontend Integration

This backend service is designed to work with a React frontend (separate repository). The frontend application will consume these REST APIs to provide a user interface for the course enrollment system.

## 🧪 Testing

Run the test suite using:
```bash
./mvnw test
```

## 📈 Monitoring

The application includes Spring Boot Actuator for monitoring and metrics. Access the actuator endpoints at:
- Health check: `http://localhost:8080/actuator/health`
- Metrics: `http://localhost:8080/actuator/metrics`

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- Shivam Parashar - Initial work

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- Neo4j team for the graph database
- All contributors who have helped shape this project 