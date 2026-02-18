package org.team.sivi.Dto.ReporteDto;

import java.math.BigDecimal;

public class TotalGananciasResponseDto {

    private BigDecimal totalGanancia;
    private Long cantidadVentas;

    public TotalGananciasResponseDto() {
    }

    public TotalGananciasResponseDto(BigDecimal totalGanancia, Long cantidadVentas) {
        this.totalGanancia = totalGanancia;
        this.cantidadVentas = cantidadVentas;
    }

    public BigDecimal getTotalGanancia() {
        return totalGanancia;
    }

    public void setTotalGanancia(BigDecimal totalGanancia) {
        this.totalGanancia = totalGanancia;
    }

    public Long getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(Long cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }
}
