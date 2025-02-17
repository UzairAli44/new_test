package com.example.fidely.fidely.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.fidely.fidely.model.User;
import com.example.fidely.fidely.service.UserService;
import com.example.fidely.fidely.util.ApiResponse;
import com.example.fidely.fidely.util.JwtUtil;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        ApiResponse<User> response = new ApiResponse<>("success", "User registered successfully", registeredUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/registernew", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<ApiResponse<User>> registerUser(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String role,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam String dob) {

        User user = new User();
        user.setAddress(address);
        user.setRole(role);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setDob(dob);

        User registeredUser = userService.registerUser(user);
        ApiResponse<User> response = new ApiResponse<>("success", "User registered successfully", registeredUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@RequestParam String email, @RequestParam String password) {
        User user = userService.findByEmail(email);

        if (user == null || !userService.validatePassword(password, user.getPassword())) {
            ApiResponse<String> response = new ApiResponse<>("error", "Invalid credentials", null);
            return ResponseEntity.badRequest().body(response);
        }

        String token = jwtUtil.generateToken(email);
        ApiResponse<String> response = new ApiResponse<>("success", "Login successful", token);
        return ResponseEntity.ok(response);
    }
}