package com.cleaner.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.cleaner.app.entity.Url;

@Repository
public interface UrlRepository extends CassandraRepository<Url, Long> {

    @AllowFiltering
    List<Url> findByCreatedAtLessThan(LocalDateTime createdAt);

}