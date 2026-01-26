package org.team.sivi.Dto.ProductoDto;

import org.team.sivi.Dto.ProductoDto.ProductoNombreCategoría.ProductoCategoriaResponseDto;
import org.team.sivi.Dto.ProductoDto.ProductoNombreMarca.ProductoMarcaResponseDto;
import org.team.sivi.Model.Enum.ProductoTipoVenta;
import org.team.sivi.Model.Enum.ProductoUnidadBase;
import java.math.BigDecimal;

public class ProductoListarVendResponseDto {
    private String codigoBarras;
    private String nombre;
    private BigDecimal precioVenta;
    private BigDecimal stockTotal;
    private BigDecimal stockMinimoAlerta;
    private Boolean bajoStock;
    private ProductoTipoVenta tipoVenta;
    private ProductoUnidadBase unidadBase;
    private ProductoCategoriaResponseDto categoria;
    private ProductoMarcaResponseDto marca;


    public ProductoListarVendResponseDto() {
    }

    public ProductoListarVendResponseDto(String codigoBarras, String nombre, BigDecimal precioVenta, BigDecimal stockTotal, BigDecimal stockMinimoAlerta, Boolean bajoStock, ProductoTipoVenta tipoVenta, ProductoUnidadBase unidadBase, ProductoCategoriaResponseDto categoria, ProductoMarcaResponseDto marca) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.stockTotal = stockTotal;
        this.stockMinimoAlerta = stockMinimoAlerta;
        this.bajoStock = bajoStock;
        this.tipoVenta = tipoVenta;
        this.unidadBase = unidadBase;
        this.categoria = categoria;
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

    public ProductoUnidadBase getUnidadBase() {
        return unidadBase;
    }

    public void setUnidadBase(ProductoUnidadBase unidadBase) {
        this.unidadBase = unidadBase;
    }

    public ProductoCategoriaResponseDto getCategoria() {
        return categoria;
    }

    public void setCategoria(ProductoCategoriaResponseDto categoria) {
        this.categoria = categoria;
    }

    public ProductoMarcaResponseDto getMarca() {
        return marca;
    }

    public void setMarca(ProductoMarcaResponseDto marca) {
        this.marca = marca;
    }
}
