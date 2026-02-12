package org.team.sivi.Dto.ReporteDto;

import java.math.BigDecimal;

public class TotalVentasDiaDto {
    private BigDecimal totalVendido;
    private Long cantidadVentas;

    public TotalVentasDiaDto(BigDecimal totalVendido, Long cantidadVentas) {
        // Si el total es null (no hay ventas), ponemos 0.00
        this.totalVendido = (totalVendido != null) ? totalVendido : BigDecimal.ZERO;
        this.cantidadVentas = (cantidadVentas != null) ? cantidadVentas : 0L;
    }

    public BigDecimal getTotalVendido() { return totalVendido; }
    public Long getCantidadVentas() { return cantidadVentas; }
}