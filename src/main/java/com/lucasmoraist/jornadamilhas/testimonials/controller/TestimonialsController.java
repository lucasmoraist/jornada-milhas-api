package com.lucasmoraist.jornadamilhas.testimonials.controller;

import com.lucasmoraist.jornadamilhas.testimonials.dto.CreateOrUpdateTestimonialsDTO;
import com.lucasmoraist.jornadamilhas.testimonials.model.Testimonials;
import com.lucasmoraist.jornadamilhas.testimonials.repository.TestimonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depoimentos")
public class TestimonialsController {

    @Autowired
    private TestimonialsRepository repository;

    @GetMapping("home")
    public ResponseEntity<List<Testimonials>> randomTestimonials(){
        return ResponseEntity.ok().body(this.repository.findRandomTestimonials());
    }

    @GetMapping("list")
    public ResponseEntity<List<Testimonials>> findAll(){
        return ResponseEntity.ok().body(this.repository.findAll());
    }

    @PostMapping("create")
    public ResponseEntity<Testimonials> create(@RequestBody CreateOrUpdateTestimonialsDTO dto){
        Testimonials newTestimonials = Testimonials.builder()
                .nameUser(dto.nameUser())
                .testimonials(dto.testimonials())
                .image(dto.image())
                .build();

        this.repository.save(newTestimonials);
        return ResponseEntity.ok().body(newTestimonials);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Testimonials> update(@PathVariable Long id, @RequestBody CreateOrUpdateTestimonialsDTO dto) {
        var testimonials = this.repository.findById(id);
        if(testimonials.isPresent()){
            var updateTestimonials = testimonials.get();
            updateTestimonials.setNameUser(dto.nameUser());
            updateTestimonials.setTestimonials(dto.testimonials());
            updateTestimonials.setImage(dto.image());

            return ResponseEntity.ok().body(updateTestimonials);
        }else{
            throw new RuntimeException("Testimonials Not Found");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        var testimonials = this.repository.findById(id).orElseThrow(() -> new RuntimeException("Testimonials"));
        this.repository.delete(testimonials);
        return ResponseEntity.ok().body("Excluído com sucesso!");
    }

}
