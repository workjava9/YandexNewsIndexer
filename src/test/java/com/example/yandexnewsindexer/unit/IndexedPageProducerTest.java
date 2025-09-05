package com.example.yandexnewsindexer.unit;

import com.example.yandexnewsindexer.kafka.IndexedPagePayload;
import com.example.yandexnewsindexer.kafka.IndexedPageProducer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IndexedPageProducerTest {

    @Test
    void send_publishesToIndexedPagesTopicWithKeyAndPayload() {
        @SuppressWarnings("unchecked")
        KafkaTemplate<String, IndexedPagePayload> kafkaTemplate = mock(KafkaTemplate.class);
        when(kafkaTemplate.send(anyString(), anyString(), any(IndexedPagePayload.class))).thenReturn(null);

        IndexedPageProducer producer = new IndexedPageProducer(kafkaTemplate);

        IndexedPagePayload payload = new IndexedPagePayload(
                "https://example.com",
                "Example title",
                "Clean text"
        );

        producer.send(payload);

        ArgumentCaptor<String> topic = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> key = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<IndexedPagePayload> body = ArgumentCaptor.forClass(IndexedPagePayload.class);

        verify(kafkaTemplate).send(topic.capture(), key.capture(), body.capture());

        assertThat(topic.getValue()).isEqualTo("indexed-pages");
        assertThat(key.getValue()).isEqualTo("https://example.com");
        assertThat(body.getValue()).isEqualTo(payload);
    }
}
