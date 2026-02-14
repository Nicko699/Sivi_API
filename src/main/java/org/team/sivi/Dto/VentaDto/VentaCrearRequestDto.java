package org.team.sivi.Dto.VentaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class VentaCrearRequestDto {
    @NotEmpty(message = "Los detalles de ventas no pueden estar vacíos")
    List<DetalleVentaCrearRequestDto>listaDetalleVenta;
    @NotBlank(message = "El metodo de pago no puede estar vacío")
    private String metodoPago;
    private BigDecimal descuentoTotal;
    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;
    @NotNull(message = "El dinero recibido no puede estar vacío")
    private BigDecimal dineroRecibido;
    @NotNull(message = "El dinero de cambio no puede estar vacío")
    private BigDecimal cambio;

    public VentaCrearRequestDto() {
    }

    public VentaCrearRequestDto(List<DetalleVentaCrearRequestDto> listaDetalleVenta, String metodoPago, BigDecimal descuentoTotal, String estado, BigDecimal dineroRecibido, BigDecimal cambio) {
        this.listaDetalleVenta = listaDetalleVenta;
        this.metodoPago = metodoPago;
        this.descuentoTotal = descuentoTotal;
        this.estado = estado;
        this.dineroRecibido = dineroRecibido;
        this.cambio = cambio;
    }

    public List<DetalleVentaCrearRequestDto> getListaDetalleVenta() {
        return listaDetalleVenta;
    }

    public void setListaDetalleVenta(List<DetalleVentaCrearRequestDto> listaDetalleVenta) {
        this.listaDetalleVenta = listaDetalleVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public BigDecimal getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(BigDecimal descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
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
