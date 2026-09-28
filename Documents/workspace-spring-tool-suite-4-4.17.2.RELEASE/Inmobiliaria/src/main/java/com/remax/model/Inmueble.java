package com.remax.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Inmuebles")
public class Inmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inmueble")
    private Integer idInmueble;

    @ManyToOne
    @JoinColumn(name = "id_captacion", nullable = false)
    private Captacion captacion;

    @ManyToOne
    @JoinColumn(name = "id_propietario", nullable = false)
    private Usuario propietario;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "tipo_inmueble", nullable = false, length = 100)
    private String tipoInmueble;

    @Column(name = "superficie_cubierta", nullable = false, precision = 10, scale = 2)
    private BigDecimal superficieCubierta;

    @Column(name = "superficie_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal superficieTotal;

    @Column(name = "cantidad_habitaciones")
    private Integer cantidadHabitaciones;

    @Column(name = "cantidad_banos")
    private Integer cantidadBanos;

    @Column(name = "cantidad_cocheras")
    private Integer cantidadCocheras;

    @Column(name = "antiguedad_estado", nullable = false, length = 50)
    private String antiguedadEstado;

    @ManyToMany
    @JoinTable(
        name = "Inmueble_Caracteristicas",
        joinColumns = @JoinColumn(name = "id_inmueble"),
        inverseJoinColumns = @JoinColumn(name = "id_caracteristica")
    )
    private List<Caracteristica> caracteristicas;

    public Inmueble() {}

    public Integer getIdInmueble() { return idInmueble; }
    public void setIdInmueble(Integer idInmueble) { this.idInmueble = idInmueble; }

    public Captacion getCaptacion() { return captacion; }
    public void setCaptacion(Captacion captacion) { this.captacion = captacion; }

    public Usuario getPropietario() { return propietario; }
    public void setPropietario(Usuario propietario) { this.propietario = propietario; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTipoInmueble() { return tipoInmueble; }
    public void setTipoInmueble(String tipoInmueble) { this.tipoInmueble = tipoInmueble; }

    public BigDecimal getSuperficieCubierta() { return superficieCubierta; }
    public void setSuperficieCubierta(BigDecimal superficieCubierta) { this.superficieCubierta = superficieCubierta; }

    public BigDecimal getSuperficieTotal() { return superficieTotal; }
    public void setSuperficieTotal(BigDecimal superficieTotal) { this.superficieTotal = superficieTotal; }

    public Integer getCantidadHabitaciones() { return cantidadHabitaciones; }
    public void setCantidadHabitaciones(Integer cantidadHabitaciones) { this.cantidadHabitaciones = cantidadHabitaciones; }

    public Integer getCantidadBanos() { return cantidadBanos; }
    public void setCantidadBanos(Integer cantidadBanos) { this.cantidadBanos = cantidadBanos; }

    public Integer getCantidadCocheras() { return cantidadCocheras; }
    public void setCantidadCocheras(Integer cantidadCocheras) { this.cantidadCocheras = cantidadCocheras; }

    public String getAntiguedadEstado() { return antiguedadEstado; }
    public void setAntiguedadEstado(String antiguedadEstado) { this.antiguedadEstado = antiguedadEstado; }

    public List<Caracteristica> getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(List<Caracteristica> caracteristicas) { this.caracteristicas = caracteristicas; }
}
