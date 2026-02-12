package org.team.sivi.Dto.VentaDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VentaListarVendResponseDto {

    private Long id;
    private String codigoVenta;
    private LocalDateTime fechaVenta;
    private BigDecimal montoTotal;
    private BigDecimal descuentoTotal;
    private String metodoPago;
    private String estado;
    private BigDecimal dineroRecibido;
    private BigDecimal cambio;

    public VentaListarVendResponseDto() {
    }

    public VentaListarVendResponseDto(Long id, String codigoVenta, LocalDateTime fechaVenta, BigDecimal montoTotal, BigDecimal descuentoTotal, String metodoPago, String estado, BigDecimal dineroRecibido, BigDecimal cambio) {
        this.id = id;
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.descuentoTotal = descuentoTotal;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.dineroRecibido = dineroRecibido;
        this.cambio = cambio;
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

    public BigDecimal getDineroRecibido() {
        return dineroRecibido;
    }

    public void setDineroRecibido(BigDecimal dineroRecibido) {
        this.dineroRecibido = dineroRecibido;
    }

    public BigDecimal getCambio() {
        return cambio;
    }

    public void setCambio(BigDecimal cambio) {
        this.cambio = cambio;
    }
}
