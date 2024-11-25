package me.jazzy.webtoontracker.repository;

import me.jazzy.webtoontracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}