package com.example.yandexnewsindexer.kafka;

import com.example.yandexnewsindexer.dto.IndexedPage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndexedPageProducer {

    private final KafkaTemplate<String, IndexedPage> kafkaTemplate;

    private static final String TOPIC = "indexing";

    public void send(IndexedPage page) {
        kafkaTemplate.send(TOPIC, page.url(), page);
    }

}