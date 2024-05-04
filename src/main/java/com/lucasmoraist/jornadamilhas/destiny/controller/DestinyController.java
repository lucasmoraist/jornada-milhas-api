package com.lucasmoraist.jornadamilhas.destiny.controller;

import com.lucasmoraist.jornadamilhas.destiny.dto.CreateOrUpdateDestinyDTO;
import com.lucasmoraist.jornadamilhas.destiny.model.Destiny;
import com.lucasmoraist.jornadamilhas.destiny.repository.DestinyRepository;
import com.lucasmoraist.jornadamilhas.destiny.service.DestinyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/destinos")
public class DestinyController {

    @Autowired
    private DestinyService service;

    @GetMapping("list")
    public ResponseEntity<List<Destiny>> findAll(){
        return ResponseEntity.ok().body(this.service.findAllDestiny());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Destiny> findDestinyById(@PathVariable Long id){
        log.info("Buscando destino com id: "+id);
        var destino = this.service.findDestinyById(id);
        return ResponseEntity.ok(destino);
    }

    @GetMapping("/{nameDestiny}")
    public ResponseEntity<Destiny> findDestinyByName(@PathVariable String nameDestiny){
        log.info("Buscando destino com nome: "+nameDestiny);
        var destiny = this.service.findDestinyByName(nameDestiny);
        return ResponseEntity.ok().body(destiny);
    }

    @PostMapping("create")
    public ResponseEntity<Destiny> create(@RequestBody CreateOrUpdateDestinyDTO dto){
        log.info("Criando destino "+dto);
        var newDestiny = this.service.createDestiny(dto);
        return ResponseEntity.ok().body(newDestiny);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Destiny> update(@PathVariable Long id, @RequestBody CreateOrUpdateDestinyDTO dto) {
        log.info("Destino do id: "+id+" atualizado. \n"+dto);
        var destiny = this.service.updateDestiny(id, dto);
        return ResponseEntity.ok(destiny);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        log.info("Excluindo destino com id: "+id);
        this.service.deleteDestiny(id);
        return ResponseEntity.ok().body("Excluído com sucesso!");
    }

}
