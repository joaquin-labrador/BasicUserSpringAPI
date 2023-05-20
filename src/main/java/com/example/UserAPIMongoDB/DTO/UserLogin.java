package com.example.UserAPIMongoDB.DTO;

public class UserLogin {
    private String email;
    private String password;

    public UserLogin() {
    }

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserLogin{" + "email='" + email + '\'' + ", password='" + password + '\'' + '}';
    }

}
