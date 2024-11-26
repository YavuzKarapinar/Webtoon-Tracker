package me.jazzy.webtoontracker.service;

import lombok.AllArgsConstructor;
import me.jazzy.webtoontracker.model.User;
import me.jazzy.webtoontracker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User updateUser(User user) {
        getUserById(user.getId());
        return userRepository.save(user);
    }
}