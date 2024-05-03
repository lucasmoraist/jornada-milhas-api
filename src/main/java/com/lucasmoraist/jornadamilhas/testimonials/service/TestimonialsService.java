package com.lucasmoraist.jornadamilhas.testimonials.service;

import com.lucasmoraist.jornadamilhas.testimonials.dto.CreateOrUpdateTestimonialsDTO;
import com.lucasmoraist.jornadamilhas.testimonials.model.Testimonials;
import com.lucasmoraist.jornadamilhas.testimonials.repository.TestimonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialsService {

    @Autowired
    private TestimonialsRepository repository;

    public List<Testimonials> randomTestimonials(){
        return this.repository.findRandomTestimonials();
    }

    public List<Testimonials> findAllTestimonials(){
        return this.repository.findAll();
    }

    public Testimonials createTestimonials(CreateOrUpdateTestimonialsDTO dto){
        Testimonials newTestimonials = Testimonials.builder()
                .nameUser(dto.nameUser())
                .testimonials(dto.testimonials())
                .image(dto.image())
                .build();

        this.repository.save(newTestimonials);
        return newTestimonials;
    }

    public Testimonials updateTestimonials(Long id, CreateOrUpdateTestimonialsDTO dto) {
        var testimonials = this.findTestimonialsById(id);

        testimonials.setNameUser(dto.nameUser());
        testimonials.setTestimonials(dto.testimonials());
        testimonials.setImage(dto.image());

        return testimonials;
    }

    public String deleteTestimonials(Long id){
        var testimonials = this.findTestimonialsById(id);
        this.repository.delete(testimonials);
        return "Excluído com sucesso!";
    }

    private Testimonials findTestimonialsById(Long id){
        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Testimonials Not Found"));
    }

}
