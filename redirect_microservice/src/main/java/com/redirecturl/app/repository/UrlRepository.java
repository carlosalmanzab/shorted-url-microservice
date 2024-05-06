package com.redirecturl.app.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.redirecturl.app.entity.Url;



@Repository
public interface UrlRepository extends CassandraRepository<Url, Long> {

    @AllowFiltering
    Optional<Url> findById(Long id);
}
