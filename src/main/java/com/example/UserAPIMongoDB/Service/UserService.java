package com.example.UserAPIMongoDB.Service;

import com.example.UserAPIMongoDB.DTO.UserLogin;
import com.example.UserAPIMongoDB.DTO.UserResponse;
import com.example.UserAPIMongoDB.Entities.User;
import com.example.UserAPIMongoDB.Repositories.UserRepositories;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserService {

    private final UserRepositories userRepositories;

    private final ModelMapper modelMapper;


    public UserService(UserRepositories userRepositories, ModelMapper modelMapper) {
        this.userRepositories = userRepositories;
        this.modelMapper = modelMapper;

    }

    public UserResponse getUserById(int id) {
        User user = userRepositories.findById(id).orElseThrow();
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setAge(Period.between(user.getBirthDate(), LocalDate.now()).getYears());
        return userResponse;
    }

    public User SaveUser(User user) {
        //hash password
        user.setPassword(hashPassword(user.getPassword()));
        return userRepositories.save(user);
    }

    private String hashPassword(String password) {
        Argon2 argon2 = Argon2Factory.create();
        // most fast Argon hashing
        return argon2.hash(1, 1024 * 1024, 1, password);
    }

    private Boolean verifyPassword(String password, String hash) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hash, password);
    }


    public Boolean LoginUser(UserLogin user, HttpServletResponse response) {
        User user1 = userRepositories.findByEmail(user.getEmail());
        if (user1 != null) {
            boolean isLogged = verifyPassword(user.getPassword(), user1.getPassword());
            if (isLogged) {
                loginSuccess(response);
                return true;
            }
            return false;
        }
        return false;
    }

    public void loginSuccess(HttpServletResponse response) {
        Cookie sessionCookie = new Cookie("session", "true");
        sessionCookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(sessionCookie);
    }

    public boolean verifyCookie(String cookie) {
        System.out.println(cookie);
        if (cookie == null) {
            return false;
        }
        return cookie.equals("true");
    }

    public boolean logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("session")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return true;
            }
        }
        return false;
    }
}

