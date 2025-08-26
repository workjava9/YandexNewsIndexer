package com.example.yandexnewsindexer.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "indexed_pages")
public class IndexedPageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String cleanText;

    private Instant timestamp;
}
