package com.shortedurl.app.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.shortedurl.app.entity.ForbiddenWord;

@Repository
public interface ForbiddenWordRepository extends CassandraRepository<ForbiddenWord, UUID> {
    
} 
