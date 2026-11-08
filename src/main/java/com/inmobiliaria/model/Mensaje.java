package com.inmobiliaria.model;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
=======
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f

@Entity
@Table(name = "mensajes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Integer idMensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conversacion", nullable = false)
<<<<<<< HEAD
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
=======
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
    private Conversacion conversacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
<<<<<<< HEAD
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
=======
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
    private Usuario usuario;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "contenido", nullable = false, columnDefinition = "TEXT")
    private String contenido;

<<<<<<< HEAD
    @Column(name = "estado", length = 50)
    private String estado;

    public Mensaje() {
    }

    public Mensaje(Conversacion conversacion, Usuario usuario, LocalDateTime fechaHora, String contenido, String estado) {
        this.conversacion = conversacion;
        this.usuario = usuario;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.estado = estado;
    }

    public Mensaje(Integer idMensaje, Conversacion conversacion, Usuario usuario, LocalDateTime fechaHora, String contenido, String estado) {
        this.idMensaje = idMensaje;
        this.conversacion = conversacion;
        this.usuario = usuario;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdMensaje() { return idMensaje; }
    public void setIdMensaje(Integer idMensaje) { this.idMensaje = idMensaje; }

    public Conversacion getConversacion() { return conversacion; }
    public void setConversacion(Conversacion conversacion) { this.conversacion = conversacion; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje mensaje = (Mensaje) o;
        return Objects.equals(idMensaje, mensaje.idMensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMensaje);
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "idMensaje=" + idMensaje +
                ", fechaHora=" + fechaHora +
                ", estado='" + estado + '\'' +
                '}';
    }
}
=======
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    // Getters y Setters
    public Integer getIdMensaje() { return idMensaje; }
    public void setIdMensaje(Integer idMensaje) { this.idMensaje = idMensaje; }
    public Conversacion getConversacion() { return conversacion; }
    public void setConversacion(Conversacion conversacion) { this.conversacion = conversacion; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
