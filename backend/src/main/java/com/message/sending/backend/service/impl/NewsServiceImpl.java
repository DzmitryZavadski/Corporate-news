package com.message.sending.backend.service.impl;

import com.message.sending.backend.model.NewsEntity;
import com.message.sending.backend.repository.NewsRepository;
import com.message.sending.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;


    @Override
    public List<NewsEntity> getAllNewsSorted() {
        return newsRepository.findAll().stream()
                .sorted((n1, n2) -> n2.getPublicationDate().compareTo(n1.getPublicationDate()))
                .collect(Collectors.toList());
    }

    @Override
    public NewsEntity createOrUpdateNews(NewsEntity news) {
        if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Заголовок новости не может быть пустым");
        }
        if (news.getText() == null || news.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Текст новости не может быть пустым");
        }
        return newsRepository.save(news);
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
