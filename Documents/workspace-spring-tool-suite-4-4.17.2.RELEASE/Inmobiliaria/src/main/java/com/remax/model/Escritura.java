package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "escrituras")
public class Escritura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escritura")
    private Integer idEscritura;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_escribania", nullable = false)
    private Escribania escribania;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(nullable = false, length = 50)
    private String estado;
}
