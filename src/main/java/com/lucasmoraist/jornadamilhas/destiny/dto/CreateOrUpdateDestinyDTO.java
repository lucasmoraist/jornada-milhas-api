package com.lucasmoraist.jornadamilhas.destiny.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public record CreateOrUpdateDestinyDTO(String nameDestiny,BigDecimal price, String meta, String description, String photo1, String photo2) {
}
