package com.shortedurl.app.repository;


import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.shortedurl.app.entity.Url;


@Repository
public interface UrlRepository extends CassandraRepository<Url, Long> {

    @AllowFiltering
    Url findByLongUrl(String longUrl);

    @AllowFiltering
    boolean existsById(long id);
}
