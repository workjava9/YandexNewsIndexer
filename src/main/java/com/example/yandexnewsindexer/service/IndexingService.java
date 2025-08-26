package com.example.yandexnewsindexer.service;

import com.example.yandexnewsindexer.HtmlCleaner.HtmlCleaner;
import com.example.yandexnewsindexer.dto.IndexedPage;
import com.example.yandexnewsindexer.kafka.IndexedPageProducer;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class IndexingService {

    private final IndexedPageProducer producer;

    public IndexedPage fetchAndClean(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .timeout(5000)
                .userAgent("YandexNewsIndexerBot/1.0")
                .get();

        String title = HtmlCleaner.extractTitle(doc);
        String cleanText = HtmlCleaner.extractCleanText(doc);

        IndexedPage result = new IndexedPage(url, title, cleanText, Instant.now());

        producer.send(result);

        return result;

    }
}
