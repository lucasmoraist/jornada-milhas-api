package com.lucasmoraist.jornadamilhas.destiny.repository;

import com.lucasmoraist.jornadamilhas.destiny.model.Destiny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DestinyRepository extends JpaRepository<Destiny, Long> {
    @Query(value = "SELECT * FROM t_destiny WHERE name_destiny = :nameDestiny", nativeQuery = true)
    Optional<Destiny> findByName(@Param("nameDestiny") String nameDestiny);
}
