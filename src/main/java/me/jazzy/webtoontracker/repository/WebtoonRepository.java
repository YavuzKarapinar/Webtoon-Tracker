package me.jazzy.webtoontracker.repository;

import me.jazzy.webtoontracker.model.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {

    Optional<List<Webtoon>> findAllByNameContainsAndUserId(String name, Long userId);

    List<Webtoon> findAllByUserId(Long userId);
}