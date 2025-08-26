package com.example.yandexnewsindexer.kafka;

import com.example.yandexnewsindexer.dto.IndexedPage;
import com.example.yandexnewsindexer.entity.IndexedPageEntity;
import com.example.yandexnewsindexer.repository.IndexedPageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IndexedPageConsumer {

    private final IndexedPageRepository repository;

    @KafkaListener(topics = "indexing", groupId = "indexing-consumer-group")
    public void consume(IndexedPage message) {
        log.info("📥 Получено из Kafka: {}", message.url());

        IndexedPageEntity entity = IndexedPageEntity.builder()
                .url(message.url())
                .title(message.title())
                .cleanText(message.cleanText())
                .timestamp(message.timestamp())
                .build();

        repository.save(entity);

        log.info("Сохранено в БД: {}", message.url());
    }
}

