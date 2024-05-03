package com.lucasmoraist.jornadamilhas.testimonials.controller;

import com.lucasmoraist.jornadamilhas.testimonials.dto.CreateOrUpdateTestimonialsDTO;
import com.lucasmoraist.jornadamilhas.testimonials.model.Testimonials;
import com.lucasmoraist.jornadamilhas.testimonials.service.TestimonialsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/depoimentos")
public class TestimonialsController {

    @Autowired
    private TestimonialsService service;

    @GetMapping("home")
    public ResponseEntity<List<Testimonials>> randomTestimonials(){
        return ResponseEntity.ok().body(this.service.randomTestimonials());
    }

    @GetMapping("list")
    public ResponseEntity<List<Testimonials>> findAll(){
        return ResponseEntity.ok().body(this.service.findAllTestimonials());
    }

    @PostMapping("create")
    public ResponseEntity<Testimonials> create(@RequestBody CreateOrUpdateTestimonialsDTO dto){
        log.info("Cadastrando depoimento "+dto);
        var newTestimonials = this.service.createTestimonials(dto);
        return ResponseEntity.ok().body(newTestimonials);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Testimonials> update(@PathVariable Long id, @RequestBody CreateOrUpdateTestimonialsDTO dto) {
        log.info("Depoimento com id: "+id+" atualizado. \n"+dto);
        var testimonials = this.service.updateTestimonials(id, dto);
        return ResponseEntity.ok(testimonials);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        log.info("Excluindo depoimento com id: "+id);
        var txt = this.service.deleteTestimonials(id);
        return ResponseEntity.ok().body(txt);
    }

}
