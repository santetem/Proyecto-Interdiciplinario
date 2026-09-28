package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 50)
    private String tag;

    @Column(nullable = false, unique = true, length = 150)
    private String correo;

    @Column(name = "upassword", nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 20)
    private String dni;
}
