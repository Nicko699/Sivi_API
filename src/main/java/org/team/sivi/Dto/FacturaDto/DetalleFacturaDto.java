package org.team.sivi.Dto.FacturaDto;

import org.team.sivi.Model.Enum.ProductoUnidadBase;

import java.math.BigDecimal;

public class DetalleFacturaDto {
    private String codigo;
    private String producto;
    private BigDecimal cantidad;
    private String um;
    private BigDecimal valor;

    public DetalleFacturaDto() {
    }

    public DetalleFacturaDto(String codigo, String producto, BigDecimal cantidad, String um, BigDecimal valor) {
        this.codigo = codigo;
        this.producto = producto;
        this.cantidad = cantidad;
        this.um = um;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
