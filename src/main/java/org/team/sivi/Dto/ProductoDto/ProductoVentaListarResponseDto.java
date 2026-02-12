package org.team.sivi.Dto.ProductoDto;

import org.team.sivi.Dto.ProductoDto.ProductoNombreMarca.ProductoMarcaResponseDto;
import org.team.sivi.Model.Enum.ProductoTipoVenta;

import java.math.BigDecimal;

public class ProductoVentaListarResponseDto {

    private String codigoBarras;
    private String nombre;
    private BigDecimal precioVenta;
    private BigDecimal stockTotal;
    private ProductoTipoVenta tipoVenta;
    private ProductoMarcaResponseDto marca;

    public ProductoVentaListarResponseDto() {
    }

    public ProductoVentaListarResponseDto(String codigoBarras, String nombre, BigDecimal precioVenta, BigDecimal stockTotal, ProductoTipoVenta tipoVenta, ProductoMarcaResponseDto marca) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.stockTotal = stockTotal;
        this.tipoVenta = tipoVenta;
        this.marca = marca;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(BigDecimal stockTotal) {
        this.stockTotal = stockTotal;
    }

    public ProductoTipoVenta getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(ProductoTipoVenta tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public ProductoMarcaResponseDto getMarca() {
        return marca;
    }

    public void setMarca(ProductoMarcaResponseDto marca) {
        this.marca = marca;
    }
}
