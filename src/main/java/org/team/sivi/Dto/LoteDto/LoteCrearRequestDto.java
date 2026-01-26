package org.team.sivi.Dto.LoteDto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class LoteCrearRequestDto {
    @NotNull(message = "Precio de la compra no puede estar vacío")
    @DecimalMin(value = "0.1",message = "El precio de compra no puede ser negativo")
    private BigDecimal precioCompraUnitario;
    @NotNull(message = "La cantidad no puede estár vacío")
    @DecimalMin(value = "0.1", message ="La cantidad no puede ser negativo")
    private BigDecimal cantidadInicial;
    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    public LoteCrearRequestDto() {
    }

    public LoteCrearRequestDto(BigDecimal precioCompraUnitario, BigDecimal cantidadInicial, Long productoId) {
        this.precioCompraUnitario = precioCompraUnitario;
        this.cantidadInicial = cantidadInicial;
        this.productoId = productoId;
    }

    public BigDecimal getPrecioCompraUnitario() {
        return precioCompraUnitario;
    }

    public void setPrecioCompraUnitario(BigDecimal precioCompraUnitario) {
        this.precioCompraUnitario = precioCompraUnitario;
    }

    public BigDecimal getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(BigDecimal cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
}
