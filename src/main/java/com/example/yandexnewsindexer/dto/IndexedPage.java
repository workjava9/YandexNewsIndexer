package com.example.yandexnewsindexer.dto;

import java.time.Instant;

public record IndexedPage(
        String url,
        String title,
        String cleanText,
        Instant timestamp
) {}

