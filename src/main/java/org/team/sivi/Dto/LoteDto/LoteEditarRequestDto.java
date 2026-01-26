package org.team.sivi.Dto.LoteDto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class LoteEditarRequestDto {

    @NotNull(message = "Precio de la compra no puede estar vacío")
    @DecimalMin(value = "0.1",message = "El precio de compra no puede ser negativo")
    private BigDecimal precioCompraUnitario;
    @NotNull(message = "La cantidad no puede estár vacío")
    @DecimalMin(value = "0.1", message ="La cantidad no puede ser negativo")
    private BigDecimal cantidadInicial;

    public LoteEditarRequestDto() {
    }

    public LoteEditarRequestDto(BigDecimal precioCompraUnitario, BigDecimal cantidadInicial) {
        this.precioCompraUnitario = precioCompraUnitario;
        this.cantidadInicial = cantidadInicial;
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
}
