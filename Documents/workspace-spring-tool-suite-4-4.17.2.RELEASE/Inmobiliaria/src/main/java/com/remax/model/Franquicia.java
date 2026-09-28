package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "franquicias")
public class Franquicia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_franquicia")
    private Integer idFranquicia;

    @ManyToOne
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SoliFranquicia solicitud;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(nullable = false, length = 50)
    private String estado;
}
