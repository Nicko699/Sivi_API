package org.team.sivi.Dto.ProductoDto;


import org.team.sivi.Model.Enum.ProductoTipoVenta;

import java.math.BigDecimal;

public class ProductoAlertarResponseDto {
    private Long id;
    private String codigoBarras;
    private String nombre;
    private BigDecimal precioVenta;
    private BigDecimal stockTotal;
    private BigDecimal stockMinimoAlerta;
    private Boolean bajoStock;
    private ProductoTipoVenta tipoVenta;;

    public ProductoAlertarResponseDto() {
    }

    public ProductoAlertarResponseDto(Long id, String codigoBarras, String nombre, BigDecimal precioVenta, BigDecimal stockTotal, BigDecimal stockMinimoAlerta, Boolean bajoStock, ProductoTipoVenta tipoVenta) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.stockTotal = stockTotal;
        this.stockMinimoAlerta = stockMinimoAlerta;
        this.bajoStock = bajoStock;
        this.tipoVenta = tipoVenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getStockMinimoAlerta() {
        return stockMinimoAlerta;
    }

    public void setStockMinimoAlerta(BigDecimal stockMinimoAlerta) {
        this.stockMinimoAlerta = stockMinimoAlerta;
    }

    public Boolean getBajoStock() {
        return bajoStock;
    }

    public void setBajoStock(Boolean bajoStock) {
        this.bajoStock = bajoStock;
    }

    public ProductoTipoVenta getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(ProductoTipoVenta tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
}
