package me.jazzy.webtoontracker.service;

import lombok.AllArgsConstructor;
import me.jazzy.webtoontracker.dto.InformationBody;
import me.jazzy.webtoontracker.model.User;
import me.jazzy.webtoontracker.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final EncryptionService encryptionService;
    private UserService userService;
    private UserRepository userRepository;

    public InformationBody registerUser(User user) {
        boolean isUserAlreadyRegistered = userRepository.findByUsernameIgnoreCase(user.getUsername()).isPresent();

        if (isUserAlreadyRegistered) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(encryptionService.encryptPassword(user.getPassword()));
        userService.saveUser(user);

        return new InformationBody(
                "Successfully registered user.",
                HttpStatus.OK.value(),
                System.currentTimeMillis()
        );
    }

    public InformationBody loginUser(User user) {
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(user.getUsername());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User userFound = optionalUser.get();
        if (!encryptionService.checkPassword(user.getPassword(), userFound.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return new InformationBody(
                "Successfully logged in.",
                HttpStatus.OK.value(),
                System.currentTimeMillis()
        );
    }
}