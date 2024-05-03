package com.lucasmoraist.jornadamilhas.destiny.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t_destiny")
@Table(name = "t_destiny")
@EqualsAndHashCode(of = "id")
public class Destiny {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 80, nullable = false)
    private String nameDestiny;
    @Column(nullable = false)
    private BigDecimal price;
    private String photo;

}
