package com.redirecturl.app.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
 
@Table("shortened_urls")
public class Url {

    @Id
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private Long id;
    
    private String longUrl;

    private LocalDateTime createdAt;

    public Url(Long id, String longUrl, LocalDateTime createdAt) {
        this.id = id;
        this.longUrl = longUrl;
        this.createdAt = createdAt;
    }

    public Url() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
