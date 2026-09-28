package com.remax.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Multimedia")
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multimedia")
    private Integer idMultimedia;

    @ManyToOne
    @JoinColumn(name = "id_inmueble", nullable = false)
    private Inmueble inmueble;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "url", length = 255)
    private String url;

    public Multimedia() {}

    public Integer getIdMultimedia() { return idMultimedia; }
    public void setIdMultimedia(Integer idMultimedia) { this.idMultimedia = idMultimedia; }

    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
