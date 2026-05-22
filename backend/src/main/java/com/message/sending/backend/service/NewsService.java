package com.message.sending.backend.service;

import com.message.sending.backend.model.NewsEntity;

import java.util.List;

public interface NewsService {
    List<NewsEntity> getAllNewsSorted();
    NewsEntity createOrUpdateNews(NewsEntity news);
    void deleteNews(Long id);
}
