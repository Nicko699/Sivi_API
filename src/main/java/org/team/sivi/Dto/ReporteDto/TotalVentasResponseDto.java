package org.team.sivi.Dto.ReporteDto;

import java.math.BigDecimal;

public class TotalVentasResponseDto {

    private BigDecimal totalVendido;
    private Long cantidadVentas;

    public TotalVentasResponseDto() {
    }

    public TotalVentasResponseDto(BigDecimal totalVendido, Long cantidadVentas) {
        this.totalVendido = totalVendido;
        this.cantidadVentas = cantidadVentas;
    }

    public BigDecimal getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(BigDecimal totalVendido) {
        this.totalVendido = totalVendido;
    }

    public Long getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(Long cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }
}
