package me.jazzy.webtoontracker.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.jazzy.webtoontracker.model.ListType;
import me.jazzy.webtoontracker.model.User;
import me.jazzy.webtoontracker.model.Webtoon;
import me.jazzy.webtoontracker.repository.WebtoonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class WebtoonService {

    private WebtoonRepository webtoonRepository;
    private UserService userService;

    public Webtoon getWebtoon(Long id) {
        return webtoonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Webtoon not found"));
    }

    public List<Webtoon> getWebtoonByName(User user, String name) {
        return webtoonRepository.findAllByNameContainsAndUserId(name, user.getId())
                .orElseThrow(() -> new RuntimeException("Webtoon not found"));
    }

    public List<Webtoon> getWebtoonsByUser(User user) {
        return webtoonRepository.findAllByUserId(user.getId());
    }

    @Transactional
    public Webtoon saveWebtoon(User user, Webtoon webtoon) {
        webtoon.setUser(user);
        Webtoon savedWebtoon = webtoonRepository.save(webtoon);

        user.getWebtoons().add(savedWebtoon);
        userService.updateUser(user);

        return savedWebtoon;
    }

    public Webtoon updateWebtoon(Webtoon theWebtoon) {
        Webtoon webtoon = getWebtoon(theWebtoon.getId());
        theWebtoon.setUser(webtoon.getUser());
        return webtoonRepository.save(webtoon);
    }

    public Webtoon deleteWebtoon(Long id) {
        Webtoon webtoon = getWebtoon(id);
        webtoonRepository.delete(webtoon);
        return webtoon;
    }

    public Webtoon patchWebtoon(Long id, Map<String, Object> updates) {
        Webtoon existingWebtoon = getWebtoon(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingWebtoon.setName((String) value);
                    break;
                case "description":
                    existingWebtoon.setDescription((String) value);
                    break;
                case "genre":
                    existingWebtoon.setGenre((String) value);
                    break;
                case "listType":
                    existingWebtoon.setListType(ListType.valueOf((String) value));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return webtoonRepository.save(existingWebtoon);
    }
}