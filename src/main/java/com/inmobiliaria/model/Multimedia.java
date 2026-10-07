package com.inmobiliaria.model;

import javax.persistence.*;

@Entity
@Table(name = "multimedia")
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multimedia")
    private Integer idMultimedia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inmueble", nullable = false)
    private Inmueble inmueble;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "url", length = 255)
    private String url;

    // Getters y Setters
    public Integer getIdMultimedia() { return idMultimedia; }
    public void setIdMultimedia(Integer idMultimedia) { this.idMultimedia = idMultimedia; }
    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
