package me.jazzy.webtoontracker.controller;

import lombok.AllArgsConstructor;
import me.jazzy.webtoontracker.model.Webtoon;
import me.jazzy.webtoontracker.service.WebtoonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/webtoons")
@AllArgsConstructor
public class WebtoonController {

    private WebtoonService webtoonService;

    @GetMapping
    public ResponseEntity<List<Webtoon>> getWebtoons() {
        return ResponseEntity.ok(webtoonService.getWebtoons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Webtoon> getWebtoon(@PathVariable Long id) {
        return ResponseEntity.ok(webtoonService.getWebtoon(id));
    }

    @PostMapping
    public ResponseEntity<Webtoon> saveWebtoon(@RequestBody Webtoon webtoon) {
        return ResponseEntity.ok(webtoonService.saveWebtoon(webtoon));
    }

    @PutMapping
    public ResponseEntity<Webtoon> updateWebtoon(@RequestBody Webtoon webtoon) {
        return ResponseEntity.ok(webtoonService.updateWebtoon(webtoon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Webtoon> deleteWebtoon(@PathVariable Long id) {
        return ResponseEntity.ok(webtoonService.deleteWebtoon(id));
    }

}