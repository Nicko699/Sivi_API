package org.team.sivi.Dto.LoteDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoteCrearResponseDto {

    private Long id;
    private String codigoLote;
    private BigDecimal precioCompraUnitario;
    private BigDecimal cantidadInicial;
    private LocalDateTime fechaCompra;
    private Boolean agotado;
    private LoteProductoCrearResponseDto producto;

    public LoteCrearResponseDto() {
    }

    public LoteCrearResponseDto(Long id, String codigoLote, BigDecimal precioCompraUnitario, BigDecimal cantidadInicial, LocalDateTime fechaCompra, Boolean agotado, LoteProductoCrearResponseDto producto) {
        this.id = id;
        this.codigoLote = codigoLote;
        this.precioCompraUnitario = precioCompraUnitario;
        this.cantidadInicial = cantidadInicial;
        this.fechaCompra = fechaCompra;
        this.agotado = agotado;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
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

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Boolean getAgotado() {
        return agotado;
    }

    public void setAgotado(Boolean agotado) {
        this.agotado = agotado;
    }

    public LoteProductoCrearResponseDto getProducto() {
        return producto;
    }

    public void setProducto(LoteProductoCrearResponseDto producto) {
        this.producto = producto;
    }
}
