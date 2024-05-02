package com.lucasmoraist.jornadamilhas.testimonials.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t_testimonials")
@Table(name = "t_testimonials")
@EqualsAndHashCode(of = "id")
public class Testimonials {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameUser;
    private String testimonials;
    private String image;

}
