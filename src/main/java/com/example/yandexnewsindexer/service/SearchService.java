package com.example.yandexnewsindexer.service;

import com.example.yandexnewsindexer.dto.SearchResult;
import com.example.yandexnewsindexer.entity.IndexedPageEntity;
import com.example.yandexnewsindexer.repository.IndexedPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final IndexedPageRepository repository;

    public List<SearchResult> search(String query) {
        if (query == null || query.isBlank()) return List.of();

        String keyword = query.trim().toLowerCase();

        List<IndexedPageEntity> results = repository.findByCleanTextContainingIgnoreCase(keyword);

        return results.stream()
                .map(p -> new SearchResult(
                        p.getUrl(),
                        p.getTitle(),
                        buildSnippet(p.getCleanText(), keyword)
                ))
                .toList();
    }

    private String buildSnippet(String text, String keyword) {
        Pattern pattern = Pattern.compile("(.{0,50}" + Pattern.quote(keyword) + ".{0,50})", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String snippet = matcher.group(1);
            return snippet.replaceAll("(?i)" + Pattern.quote(keyword), "<b>$0</b>");
        }

        return text.length() > 100 ? text.substring(0, 100) + "..." : text;
    }
}
