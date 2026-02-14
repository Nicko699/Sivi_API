package org.team.sivi.Dto.FacturaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class FacturaCrearRequestDto {
    @NotBlank(message = "El código venta es obligatorio")
    private String codigoVenta;
    @NotNull(message = "El dinero recibido es obligatorio")
    private BigDecimal dineroRecibido;
    @NotNull(message = "El dinero devuelto es obligatorio")
    private BigDecimal dineroDevuelto;

    public FacturaCrearRequestDto() {
    }

    public FacturaCrearRequestDto(String codigoVenta, BigDecimal dineroRecibido, BigDecimal dineroDevuelto) {
        this.codigoVenta = codigoVenta;
        this.dineroRecibido = dineroRecibido;
        this.dineroDevuelto = dineroDevuelto;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public BigDecimal getDineroRecibido() {
        return dineroRecibido;
    }

    public void setDineroRecibido(BigDecimal dineroRecibido) {
        this.dineroRecibido = dineroRecibido;
    }

    public BigDecimal getDineroDevuelto() {
        return dineroDevuelto;
    }

    public void setDineroDevuelto(BigDecimal dineroDevuelto) {
        this.dineroDevuelto = dineroDevuelto;
    }
}
