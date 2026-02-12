package org.team.sivi.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoVenta;
    private BigDecimal montoTotal;
    private LocalDateTime fechaVenta;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    // Getters y Setters manuales (Estilo Nico)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigoVenta() { return codigoVenta; }
    public void setCodigoVenta(String codigoVenta) { this.codigoVenta = codigoVenta; }
    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}