package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "escribanias")
public class Escribania {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escribania")
    private Integer idEscribania;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 50)
    private String telefono;

    @Column(length = 150)
    private String correo;
}
