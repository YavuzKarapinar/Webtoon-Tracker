package me.jazzy.webtoontracker.controller;

import lombok.AllArgsConstructor;
import me.jazzy.webtoontracker.dto.InformationBody;
import me.jazzy.webtoontracker.model.User;
import me.jazzy.webtoontracker.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<InformationBody> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<InformationBody> login(@RequestBody User user) {
        return ResponseEntity.ok(authService.loginUser(user));
    }
}