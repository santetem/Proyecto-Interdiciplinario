package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
<<<<<<< HEAD
import java.util.Objects;
=======
import java.util.List;
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f

@Entity
@Table(name = "inmuebles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Inmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inmueble")
    private Integer idInmueble;

    @ManyToOne(fetch = FetchType.LAZY)
<<<<<<< HEAD
    @JoinColumn(name = "id_captacion", nullable = false)
=======
    @JoinColumn(name = "id_captacion")
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Captacion captacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario", nullable = false)
<<<<<<< HEAD
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
=======
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
    private Usuario propietario;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

<<<<<<< HEAD
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
=======
    @Column(name = "tipo_inmueble", nullable = false, length = 100)
    private String tipoInmueble;

    @Column(name = "superficie_cubierta", nullable = false, precision = 10, scale = 2)
    private BigDecimal superficieCubierta;

    @Column(name = "superficie_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal superficieTotal;

    @Column(name = "cantidad_habitaciones")
    private Integer cantidadHabitaciones = 0;

    @Column(name = "cantidad_banos")
    private Integer cantidadBanos = 0;

    @Column(name = "cantidad_cocheras")
    private Integer cantidadCocheras = 0;

    @Column(name = "antiguedad_estado", nullable = false, length = 50)
    private String antiguedadEstado;

    @ManyToMany
    @JoinTable(
        name = "inmueble_caracteristicas",
        joinColumns = @JoinColumn(name = "id_inmueble"),
        inverseJoinColumns = @JoinColumn(name = "id_caracteristica")
    )
    private List<Caracteristica> caracteristicas;

    // Getters y Setters
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
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
}