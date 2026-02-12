package org.team.sivi.Dto.VentaDto;

import org.team.sivi.Model.DetalleVenta;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VentaCrearResponseDto {
    private Long id;
    private String codigoVenta;
    private String nombreUsuario;
    private String rolUsuario;
    private String email;
    private LocalDateTime fechaVenta;
    private BigDecimal montoTotal;
    private BigDecimal descuentoTotal;
    private String metodoPago;
    private String estado;

    public VentaCrearResponseDto() {
    }

    public VentaCrearResponseDto(Long id, String codigoVenta, String nombreUsuario, String rolUsuario, String email, LocalDateTime fechaVenta, BigDecimal montoTotal, BigDecimal descuentoTotal, String metodoPago, String estado) {
        this.id = id;
        this.codigoVenta = codigoVenta;
        this.nombreUsuario = nombreUsuario;
        this.rolUsuario = rolUsuario;
        this.email = email;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.descuentoTotal = descuentoTotal;
        this.metodoPago = metodoPago;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public BigDecimal getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(BigDecimal descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
