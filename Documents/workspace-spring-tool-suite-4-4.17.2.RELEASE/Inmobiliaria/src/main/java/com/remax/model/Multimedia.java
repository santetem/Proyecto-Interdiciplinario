package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "multimedia")
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multimedia")
    private Integer idMultimedia;

    @ManyToOne
    @JoinColumn(name = "id_inmueble", nullable = false)
    private Inmueble inmueble;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(length = 255)
    private String url;
}
