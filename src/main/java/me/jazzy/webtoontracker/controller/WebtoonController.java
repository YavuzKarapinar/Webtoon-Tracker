package me.jazzy.webtoontracker.controller;

import lombok.AllArgsConstructor;
import me.jazzy.webtoontracker.model.User;
import me.jazzy.webtoontracker.model.Webtoon;
import me.jazzy.webtoontracker.service.WebtoonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/webtoons")
@AllArgsConstructor
public class WebtoonController {

    private WebtoonService webtoonService;

    @GetMapping
    public ResponseEntity<List<Webtoon>> getWebtoons(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(webtoonService.getWebtoonsByUser(user));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Webtoon>> getWebtoonByName(@AuthenticationPrincipal User user, @PathVariable String name) {
        return ResponseEntity.ok(webtoonService.getWebtoonByName(user, name));
    }

    @PostMapping
    public ResponseEntity<Webtoon> saveWebtoon(@AuthenticationPrincipal User user, @RequestBody Webtoon webtoon) {
        return ResponseEntity.ok(webtoonService.saveWebtoon(user, webtoon));
    }

    @PutMapping
    public ResponseEntity<Webtoon> updateWebtoon(@RequestBody Webtoon webtoon) {
        return ResponseEntity.ok(webtoonService.updateWebtoon(webtoon));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Webtoon> patchWebtoon(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Webtoon updatedWebtoon = webtoonService.patchWebtoon(id, updates);
        return ResponseEntity.ok(updatedWebtoon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Webtoon> deleteWebtoon(@PathVariable Long id) {
        return ResponseEntity.ok(webtoonService.deleteWebtoon(id));
    }

}