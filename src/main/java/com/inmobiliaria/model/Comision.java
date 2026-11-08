package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "comisiones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comision")
    private Integer idComision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operacion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Operacion operacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @Column(name = "porcentaje", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "monto", nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Comision() {
    }

    public Comision(Operacion operacion, Usuario usuario, BigDecimal porcentaje, BigDecimal monto, String estado) {
        this.operacion = operacion;
        this.usuario = usuario;
        this.porcentaje = porcentaje;
        this.monto = monto;
        this.estado = estado;
    }

    public Comision(Integer idComision, Operacion operacion, Usuario usuario, BigDecimal porcentaje, BigDecimal monto, String estado) {
        this.idComision = idComision;
        this.operacion = operacion;
        this.usuario = usuario;
        this.porcentaje = porcentaje;
        this.monto = monto;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdComision() { return idComision; }
    public void setIdComision(Integer idComision) { this.idComision = idComision; }

    public Operacion getOperacion() { return operacion; }
    public void setOperacion(Operacion operacion) { this.operacion = operacion; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comision comision = (Comision) o;
        return Objects.equals(idComision, comision.idComision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComision);
    }

    @Override
    public String toString() {
        return "Comision{" +
                "idComision=" + idComision +
                ", porcentaje=" + porcentaje +
                ", monto=" + monto +
                ", estado='" + estado + '\'' +
                '}';
    }
}