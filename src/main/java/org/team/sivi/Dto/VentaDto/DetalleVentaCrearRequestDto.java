package org.team.sivi.Dto.VentaDto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class DetalleVentaCrearRequestDto {
   @NotNull(message = "El código de barras del producto no puede estar vacío")
    private String codigoBarrasProducto;
   @NotNull(message = "La cantidad no puede estar vacía")
   @DecimalMin(value = "0.01", message = "La cantidad no puede ser negativa")
    private BigDecimal cantidad;

    public DetalleVentaCrearRequestDto() {
    }

    public DetalleVentaCrearRequestDto(String codigoBarrasProducto, BigDecimal cantidad) {
        this.codigoBarrasProducto = codigoBarrasProducto;
        this.cantidad = cantidad;
    }

    public String getCodigoBarrasProducto() {
        return codigoBarrasProducto;
    }

    public void setCodigoBarrasProducto(String codigoBarrasProducto) {
        this.codigoBarrasProducto = codigoBarrasProducto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}
