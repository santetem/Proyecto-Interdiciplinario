package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "inmuebles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Inmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inmueble")
    private Integer idInmueble;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_captacion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Captacion captacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario propietario;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "tipo_inmueble", nullable = false, length = 50)
    private String tipoInmueble;

    @Column(name = "superficie_cubierta", precision = 10, scale = 2)
    private BigDecimal superficieCubierta;

    @Column(name = "superficie_total", precision = 10, scale = 2)
    private BigDecimal superficieTotal;

    @Column(name = "cantidad_habitaciones")
    private Integer cantidadHabitaciones;

    @Column(name = "cantidad_banos")
    private Integer cantidadBanos;

    @Column(name = "cantidad_cocheras")
    private Integer cantidadCocheras;

    @Column(name = "antiguedad_estado", length = 100)
    private String antiguedadEstado;

    @Column(name = "localidad", nullable = false, length = 100)
    private String localidad;

    public Inmueble() {
    }

    public Inmueble(Captacion captacion, Usuario propietario, String direccion, String tipoInmueble, BigDecimal superficieCubierta, BigDecimal superficieTotal, Integer cantidadHabitaciones, Integer cantidadBanos, Integer cantidadCocheras, String antiguedadEstado, String localidad) {
        this.captacion = captacion;
        this.propietario = propietario;
        this.direccion = direccion;
        this.tipoInmueble = tipoInmueble;
        this.superficieCubierta = superficieCubierta;
        this.superficieTotal = superficieTotal;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.cantidadBanos = cantidadBanos;
        this.cantidadCocheras = cantidadCocheras;
        this.antiguedadEstado = antiguedadEstado;
        this.localidad = localidad;
    }

    public Inmueble(Integer idInmueble, Captacion captacion, Usuario propietario, String direccion, String tipoInmueble, BigDecimal superficieCubierta, BigDecimal superficieTotal, Integer cantidadHabitaciones, Integer cantidadBanos, Integer cantidadCocheras, String antiguedadEstado, String localidad) {
        this.idInmueble = idInmueble;
        this.captacion = captacion;
        this.propietario = propietario;
        this.direccion = direccion;
        this.tipoInmueble = tipoInmueble;
        this.superficieCubierta = superficieCubierta;
        this.superficieTotal = superficieTotal;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.cantidadBanos = cantidadBanos;
        this.cantidadCocheras = cantidadCocheras;
        this.antiguedadEstado = antiguedadEstado;
        this.localidad = localidad;
    }

    // --- GETTERS Y SETTERS ---
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

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return Objects.equals(idInmueble, inmueble.idInmueble);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInmueble);
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "idInmueble=" + idInmueble +
                ", direccion='" + direccion + '\'' +
                ", tipoInmueble='" + tipoInmueble + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}