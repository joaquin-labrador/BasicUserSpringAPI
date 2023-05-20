# BasicUserSpringAPI

## Description

This is a basic API for user management using Spring Boot and MySQL. Not use Spring Security.
It is a simple API for learning purposes.
I used the following technologies:
- Spring Boot
- Spring Data JPA
- MySQL
- Gradle
- Argon2 (for password hashing)
In the cookies, I create my own cookie name "session" this have "true" value if the user is logged and en the logout I delete the cookie. 
This practice is not secure, it is only for learning purposes.
# Endpoints
## User
``
GET /api/v1/user/{id} It shows the user with the id
``
POST /api/v1/user/register It creates a new user
``
