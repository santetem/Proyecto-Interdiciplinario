package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "operaciones")
public class Operacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion")
    private Integer idOperacion;

    @ManyToOne
    @JoinColumn(name = "id_oferta", nullable = false)
    private Oferta oferta;

    @Column(name = "tipo_operacion", nullable = false, length = 50)
    private String tipoOperacion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 50)
    private String estado;
}
