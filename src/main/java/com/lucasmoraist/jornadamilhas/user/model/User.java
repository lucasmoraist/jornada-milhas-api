package com.lucasmoraist.jornadamilhas.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t_user")
@Table(name = "t_user")
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 120,nullable = false)
    private String name;
    @Email(message = "Digite um email válido.")
    @Column(nullable = false ,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

}
