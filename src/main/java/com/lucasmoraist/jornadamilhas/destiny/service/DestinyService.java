package com.lucasmoraist.jornadamilhas.destiny.service;

import com.lucasmoraist.jornadamilhas.destiny.dto.CreateOrUpdateDestinyDTO;
import com.lucasmoraist.jornadamilhas.destiny.model.Destiny;
import com.lucasmoraist.jornadamilhas.destiny.repository.DestinyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class DestinyService {

    @Autowired
    private DestinyRepository repository;

    public List<Destiny> findAllDestiny(){
        return this.repository.findAll();
    }

    public Destiny findDestinyByName(String nameDestiny){
        return this.repository.findByName(nameDestiny)
                .orElseThrow(() -> new RuntimeException("Nenhum destino foi encontrado"));
    }

    public Destiny createDestiny(CreateOrUpdateDestinyDTO dto){
        Destiny newDestiny = Destiny.builder()
                .nameDestiny(dto.nameDestiny())
                .price(dto.price())
                .photo(dto.photo())
                .build();

        this.repository.save(newDestiny);
        return newDestiny;
    }

    public Destiny updateDestiny(Long id, CreateOrUpdateDestinyDTO dto) {
        var destiny = this.findDestinyById(id);

        destiny.setNameDestiny(dto.nameDestiny());
        destiny.setPrice(dto.price());
        destiny.setPhoto(dto.photo());

        return destiny;
    }

    public String deleteDestiny(Long id){
        var destiny = this.findDestinyById(id);
        this.repository.delete(destiny);
        return "Excluído com sucesso!";
    }

    private Destiny findDestinyById(Long id){
        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Destiny Not Found"));
    }

}
