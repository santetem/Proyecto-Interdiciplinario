package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "multimedia")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multimedia")
    private Integer idMultimedia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inmueble", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Inmueble inmueble;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    public Multimedia() {
    }

    public Multimedia(Inmueble inmueble, String tipo, String url) {
        this.inmueble = inmueble;
        this.tipo = tipo;
        this.url = url;
    }

    public Multimedia(Integer idMultimedia, Inmueble inmueble, String tipo, String url) {
        this.idMultimedia = idMultimedia;
        this.inmueble = inmueble;
        this.tipo = tipo;
        this.url = url;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdMultimedia() { return idMultimedia; }
    public void setIdMultimedia(Integer idMultimedia) { this.idMultimedia = idMultimedia; }

    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multimedia that = (Multimedia) o;
        return Objects.equals(idMultimedia, that.idMultimedia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMultimedia);
    }

    @Override
    public String toString() {
        return "Multimedia{" +
                "idMultimedia=" + idMultimedia +
                ", tipo='" + tipo + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}