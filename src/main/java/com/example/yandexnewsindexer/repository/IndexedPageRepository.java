package com.example.yandexnewsindexer.repository;

import com.example.yandexnewsindexer.entity.IndexedPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IndexedPageRepository extends JpaRepository<IndexedPageEntity, Long> {

    List<IndexedPageEntity> findByCleanTextContainingIgnoreCase(String keyword);

}

