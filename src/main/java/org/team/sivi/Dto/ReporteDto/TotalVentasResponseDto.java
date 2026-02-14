package org.team.sivi.Dto.ReporteDto;

import java.math.BigDecimal;

public class TotalVentasResponseDto {

    private BigDecimal totalVendido;
    private BigDecimal totalGanancia;
    private Long cantidadVentas;

    public TotalVentasResponseDto() {
    }

    public TotalVentasResponseDto(BigDecimal totalVendido, BigDecimal totalGanancia, Long cantidadVentas) {
        this.totalVendido = (totalVendido != null) ? totalVendido : BigDecimal.ZERO;
        this.totalGanancia = (totalGanancia != null) ? totalGanancia : BigDecimal.ZERO;
        this.cantidadVentas = (cantidadVentas != null) ? cantidadVentas : 0L;
    }

    // Getters
    public BigDecimal getTotalVendido() { return totalVendido; }
    public BigDecimal getTotalGanancia() { return totalGanancia; }
    public Long getCantidadVentas() { return cantidadVentas; }

    // Setters
    public void setTotalVendido(BigDecimal totalVendido) { this.totalVendido = totalVendido; }
    public void setTotalGanancia(BigDecimal totalGanancia) { this.totalGanancia = totalGanancia; }
    public void setCantidadVentas(Long cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }
}
