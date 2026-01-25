package org.team.sivi.Dto.LoteDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoteListarResponseDto {
    private Long id;
    private String codigoLote;
    private BigDecimal precioCompraUnitario;
    private BigDecimal cantidadInicial;
    private BigDecimal cantidadActual;
    private LocalDateTime fechaCompra;
    private Boolean agotado;
    private LoteProductoListarResponseDto producto;

    public LoteListarResponseDto() {
    }

    public LoteListarResponseDto(Long id, String codigoLote, BigDecimal precioCompraUnitario, BigDecimal cantidadInicial, BigDecimal cantidadActual, LocalDateTime fechaCompra, Boolean agotado, LoteProductoListarResponseDto producto) {
        this.id = id;
        this.codigoLote = codigoLote;
        this.precioCompraUnitario = precioCompraUnitario;
        this.cantidadInicial = cantidadInicial;
        this.cantidadActual = cantidadActual;
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

    public BigDecimal getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(BigDecimal cantidadActual) {
        this.cantidadActual = cantidadActual;
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

    public LoteProductoListarResponseDto getProducto() {
        return producto;
    }

    public void setProducto(LoteProductoListarResponseDto producto) {
        this.producto = producto;
    }
}

