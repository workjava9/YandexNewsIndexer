package com.example.yandexnewsindexer.controller;

import com.example.yandexnewsindexer.dto.IndexedPage;
import com.example.yandexnewsindexer.service.IndexingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/index")
public class IndexingController {

    private final IndexingService indexingService;

    @GetMapping
    public IndexedPage indexUrl(@RequestParam String url) throws Exception {
        return indexingService.fetchAndClean(url);
    }
}
