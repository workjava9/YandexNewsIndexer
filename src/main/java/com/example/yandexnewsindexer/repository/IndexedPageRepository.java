package com.example.yandexnewsindexer.repository;

import com.example.yandexnewsindexer.entity.IndexedPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndexedPageRepository extends JpaRepository<IndexedPageEntity, Long> {
}

