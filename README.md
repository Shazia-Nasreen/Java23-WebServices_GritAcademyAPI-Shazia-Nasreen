GritAcademyAPI
GritAcademyAPI is a backend API for Grit Academy, an educational platform that provides resources, courses, and support for students. This API serves as the backbone for user management, course management, and student progress tracking, ensuring a seamless experience for both students and educators.

Features
User Authentication: Secure user registration, login, and authentication using JWT tokens.
Course Management: Create, update, and retrieve courses and lessons.
Student Progress Tracking: Track students’ progress through courses and lessons.
Modular Architecture: Easy to extend with new features or integrate with other services.
RESTful API: Designed with RESTful principles for clean and easy-to-use endpoints.
Technologies Used
Java – The primary programming language used for building the API.
Spring Boot – Framework for creating stand-alone, production-grade Spring-based applications.
JPA – Java Persistence API for interacting with the database.
JWT – JSON Web Tokens for secure user authentication.
MySQL – Relational database management system to store application data.
Maven – Build automation tool to manage dependencies and project structure.

POST /api/auth/register – Register a new user
POST /api/auth/login – Log in and receive a JWT token
Courses
GET /api/courses – Get a list of all courses
POST /api/courses – Create a new course (Admin only)
GET /api/courses/{id} – Get a specific course by ID
PUT /api/courses/{id} – Update a course (Admin only)
Students
GET /api/students – Get a list of all students
POST /api/students – Add a new student
GET /api/students/{id} – Get a specific student's details by ID
