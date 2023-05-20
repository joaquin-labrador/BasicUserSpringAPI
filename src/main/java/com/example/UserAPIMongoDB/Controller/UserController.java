package com.example.UserAPIMongoDB.Controller;

import com.example.UserAPIMongoDB.DTO.UserLogin;
import com.example.UserAPIMongoDB.DTO.UserResponse;
import com.example.UserAPIMongoDB.Entities.User;
import com.example.UserAPIMongoDB.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //This annotation is used to convert the return value and write it to the http response automatically (in this case, it converts the UserResponse object to JSON)
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<User> SaveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.SaveUser(user));
    }

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<String> LoginUser(@RequestBody UserLogin user, HttpServletResponse response) {
        boolean login = userService.LoginUser(user, response);
        if (login) {
            return ResponseEntity.ok("Login Successful");
        }
        return ResponseEntity.badRequest().body("Login Failed");
    }

    @GetMapping("/verifyCookie")
    @ResponseBody
    public ResponseEntity<String> verifyCookie(@CookieValue(name = "session") String cookieValue) {
        boolean isLogged = userService.verifyCookie(cookieValue);

        if (isLogged) {
            return ResponseEntity.ok("Cookie Verified");
        }
        return ResponseEntity.badRequest().body("Cookie Not Verified");


    }

    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse responsee) {
        try {
            boolean isLogged = userService.logout(request, responsee);

            if (isLogged) {
                return ResponseEntity.ok("Logout Successful");
            }
            return ResponseEntity.badRequest().body("Logout Failed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Logout Failed");
        }

    }
}
