package com.message.sending.backend.repository;

import com.message.sending.backend.model.NewsEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class NewsRepository {
    private final List<NewsEntity> database = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public NewsRepository() {
        save(new NewsEntity(null, "Запуск новой фичи", "Мы перешли на упрощенную архитектуру бэкенда!", LocalDateTime.now().minusDays(2)));
        save(new NewsEntity(null, "Важное объявление", "Завтра в офисе день пиццы, ждем всех.", LocalDateTime.now().minusDays(1)));
        save(new NewsEntity(null, "Плановые работы", "Серверы обновятся сегодня в полночь.", LocalDateTime.now()));
    }

    public List<NewsEntity> findAll() {
        return database;
    }

    public NewsEntity save(NewsEntity news) {
        if (news.getId() == null) {
            news.setId(idGenerator.getAndIncrement());
            if (news.getPublicationDate() == null) {
                news.setPublicationDate(LocalDateTime.now());
            }
            database.add(news);
            return news;
        } else {
            // Если ID есть, значит это редактирование: удаляем старую версию, кладем новую
            deleteById(news.getId());
            database.add(news);
            return news;
        }
    }

    public void deleteById(Long id) {
        database.removeIf(news -> news.getId().equals(id));
    }

}
