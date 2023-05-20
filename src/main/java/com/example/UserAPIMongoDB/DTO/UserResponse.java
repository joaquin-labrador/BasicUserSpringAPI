package com.example.UserAPIMongoDB.DTO;


public class UserResponse {
    private String email;
    private String username;
    private Integer age;

    public UserResponse() {
    }

    public UserResponse(String email, String username, Integer age) {
        this.email = email;
        this.username = username;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserResponse{" + "email='" + email + '\'' + ", username='" + username + '\'' + ", age=" + age + '}';
    }
}
