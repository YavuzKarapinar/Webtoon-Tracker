package me.jazzy.webtoontracker.service;

import lombok.AllArgsConstructor;
import me.jazzy.webtoontracker.model.Webtoon;
import me.jazzy.webtoontracker.repository.WebtoonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WebtoonService {

    private WebtoonRepository webtoonRepository;

    public List<Webtoon> getWebtoons() {
        return webtoonRepository.findAll();
    }

    public Webtoon getWebtoon(Long id) {
        return webtoonRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Webtoon not found"));
    }

    public Webtoon saveWebtoon(Webtoon webtoon) {
        return webtoonRepository.save(webtoon);
    }

    public Webtoon updateWebtoon(Webtoon webtoon) {
        getWebtoon(webtoon.getId());
        return webtoonRepository.save(webtoon);
    }

    public Webtoon deleteWebtoon(Long id) {
        Webtoon webtoon = getWebtoon(id);
        webtoonRepository.delete(webtoon);
        return webtoon;
    }
}