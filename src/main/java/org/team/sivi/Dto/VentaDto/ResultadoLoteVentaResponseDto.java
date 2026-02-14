package org.team.sivi.Dto.VentaDto;

import org.team.sivi.Model.Lote;

import java.math.BigDecimal;
import java.util.List;

public class ResultadoLoteVentaResponseDto {

    private List<Lote> lotesUsados;
    private BigDecimal costoTotal;

    public ResultadoLoteVentaResponseDto() {
    }

    public ResultadoLoteVentaResponseDto(List<Lote> lotesUsados, BigDecimal costoTotal) {
        this.lotesUsados = lotesUsados;
        this.costoTotal = costoTotal;
    }

    public List<Lote> getLotesUsados() {
        return lotesUsados;
    }

    public void setLotesUsados(List<Lote> lotesUsados) {
        this.lotesUsados = lotesUsados;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }
}
