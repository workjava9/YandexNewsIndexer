package com.example.yandexnewsindexer.kafka;

import lombok.Data;

@Data
public class IndexedPagePayload {
    private String url;
    private String title;
    private String content;
}
