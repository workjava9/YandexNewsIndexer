package com.example.yandexnewsindexer.unit;

import com.example.yandexnewsindexer.entity.IndexedPageEntity;
import com.example.yandexnewsindexer.kafka.IndexedPageConsumer;
import com.example.yandexnewsindexer.kafka.IndexedPagePayload;
import com.example.yandexnewsindexer.repository.IndexedPageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IndexedPageConsumerTest {

    @Test
    void consumer_persistsIncomingMessage() throws Exception {
        IndexedPageRepository repo = mock(IndexedPageRepository.class);
        IndexedPageConsumer consumer = new IndexedPageConsumer(repo);

        IndexedPagePayload message = new IndexedPagePayload(
                "https://example.com/a",
                "A title",
                "Some content"
        );

        Method handler = Arrays.stream(IndexedPageConsumer.class.getDeclaredMethods())
                .filter(m -> m.getParameterCount() == 1 && m.getParameterTypes()[0].equals(IndexedPagePayload.class))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No handler method taking IndexedPagePayload found"));

        handler.setAccessible(true);
        handler.invoke(consumer, message);

        ArgumentCaptor<IndexedPageEntity> captor = ArgumentCaptor.forClass(IndexedPageEntity.class);
        verify(repo).save(captor.capture());

        IndexedPageEntity saved = captor.getValue();
        assertThat(saved.getUrl()).isEqualTo("https://example.com/a");
        assertThat(saved.getTitle()).isEqualTo("A title");
        assertThat(saved.getContent()).isEqualTo("Some content");
        assertThat(saved.getIndexedAt()).isNotNull();
    }
}
