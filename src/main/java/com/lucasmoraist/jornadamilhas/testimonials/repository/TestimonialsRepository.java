package com.lucasmoraist.jornadamilhas.testimonials.repository;

import com.lucasmoraist.jornadamilhas.testimonials.model.Testimonials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestimonialsRepository extends JpaRepository<Testimonials, Long> {

    @Query(value = "SELECT * FROM t_testimonials ORDER BY RAND() LIMIT 3;", nativeQuery = true)
    List<Testimonials> findRandomTestimonials();

}
