package com.lucasmoraist.jornadamilhas.destiny.controller;

import com.lucasmoraist.jornadamilhas.destiny.dto.CreateOrUpdateDestinyDTO;
import com.lucasmoraist.jornadamilhas.destiny.model.Destiny;
import com.lucasmoraist.jornadamilhas.destiny.repository.DestinyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destinos")
public class DestinyController {

    @Autowired
    private DestinyRepository repository;

    @GetMapping("list")
    public ResponseEntity<List<Destiny>> findAll(){
        return ResponseEntity.ok().body(this.repository.findAll());
    }

    @GetMapping("/{nameDestiny}")
    public ResponseEntity<Destiny> findDestinyByName(@PathVariable String nameDestiny){
        var destiny = this.repository.findByName(nameDestiny).orElseThrow(() -> new RuntimeException("Nenhum destino foi encontrado"));
        return ResponseEntity.ok().body(destiny);
    }

    @PostMapping("create")
    public ResponseEntity<Destiny> create(@RequestBody CreateOrUpdateDestinyDTO dto){
        Destiny newDestiny = Destiny.builder()
                .nameDestiny(dto.nameDestiny())
                .price(dto.price())
                .photo(dto.photo())
                .build();

        this.repository.save(newDestiny);
        return ResponseEntity.ok().body(newDestiny);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Destiny> update(@PathVariable Long id, @RequestBody CreateOrUpdateDestinyDTO dto) {
        var destiny = this.repository.findById(id);
        if(destiny.isPresent()){
            var updateDestiny = destiny.get();
            updateDestiny.setNameDestiny(dto.nameDestiny());
            updateDestiny.setPrice(dto.price());
            updateDestiny.setPhoto(dto.photo());

            return ResponseEntity.ok().body(updateDestiny);
        }else{
            throw new RuntimeException("Destiny Not Found");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        var destiny = this.repository.findById(id).orElseThrow(() -> new RuntimeException("Destiny not found"));
        this.repository.delete(destiny);
        return ResponseEntity.ok().body("Excluído com sucesso!");
    }

}
