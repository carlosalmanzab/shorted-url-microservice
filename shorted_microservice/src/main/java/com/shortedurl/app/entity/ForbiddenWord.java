package com.shortedurl.app.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("forbidden_words")
public class ForbiddenWord {

    @PrimaryKey
    private UUID id;
    private String word;
    private LocalDateTime createdAt;

    public ForbiddenWord(UUID id, String word, LocalDateTime createdAt) {
        this.id = id;
        this.word = word;
        this.createdAt = createdAt;
    }

    public ForbiddenWord() {
        
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
}
