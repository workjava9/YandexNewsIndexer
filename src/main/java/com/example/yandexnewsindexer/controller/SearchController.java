package com.example.yandexnewsindexer.controller;

import com.example.yandexnewsindexer.dto.SearchResult;
import com.example.yandexnewsindexer.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public List<SearchResult> search(@RequestParam String query) {
        return searchService.search(query);
    }
}
