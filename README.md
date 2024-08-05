# Blog Application Backend

This is the backend of a blog application, built with Java and Spring Boot. The backend provides RESTful APIs for managing blog posts and comments.

## Features

- CRUD operations for blog posts
- CRUD operations for comments
- User authentication and authorization
- Exception handling

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Hibernate/JPA
- PostgreSQL

## Setup and Installation

1. **Clone the repository:**

   git clone <repository-url>

   cd backend

2. **Configure Database:**

Update `application.properties` with your PostgreSQL database details:

    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
3. **Run the application:**

The backend will be available at `http://localhost:8080`.

## API Endpoints

### Authentication

- `POST /api/auth/login`: User login.

### Posts

- `GET /api/posts`: Get all posts.
- `GET /api/posts/{id}`: Get a single post by ID.
- `POST /api/posts`: Create a new post.
- `PUT /api/posts/{id}`: Update a post by ID.
- `DELETE /api/posts/{id}`: Delete a post by ID.

### Comments

- `GET /api/posts/{postId}/comments`: Get all comments for a post.
- `POST /api/posts/{postId}/comments`: Add a comment to a post.

## Project Structure

- **controllers**: REST API controllers.
- **services**: Business logic layer.
- **models**: Entity classes and DTOs.
- **repositories**: Data access layer interfaces.
- **exceptions**: Custom exception classes and handlers.
- **security**: Security configuration and JWT utilities.

## Usage

### Running the Application

Make sure the database is running and accessible. Use the provided Maven wrapper to build and run the application.

### API Testing

You can use tools like Postman or Insomnia to test the API endpoints.

## Contributing

Feel free to fork the project and make your contributions. Please create a new branch for your features and make a pull request.

## License

This project is open source and available under the MIT License.
