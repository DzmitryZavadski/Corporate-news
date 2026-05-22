package com.message.sending.backend.controller;

import com.message.sending.backend.model.NewsEntity;
import com.message.sending.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsEntity>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNewsSorted());
    }

    @PostMapping
    public ResponseEntity<NewsEntity> createNews(@RequestBody NewsEntity news) {
        NewsEntity newsEntity = newsService.createOrUpdateNews(news);
        return new ResponseEntity<>(newsEntity, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<NewsEntity>> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}
