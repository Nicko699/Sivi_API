package org.team.sivi.Dto.ProductoDto;
import org.team.sivi.Dto.ProductoDto.ProductoNombreCategoría.ProductoCategoriaResponseDto;
import org.team.sivi.Dto.ProductoDto.ProductoNombreMarca.ProductoMarcaResponseDto;
import org.team.sivi.Model.Enum.ProductoTipoVenta;
import org.team.sivi.Model.Enum.ProductoUnidadBase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductoCrearResponseDto {
    private Long id;
    private String codigoBarras;
    private String nombre;
    private String descripcion;
    private BigDecimal precioVenta;
    private BigDecimal stockTotal;;
    private BigDecimal stockMinimoAlerta;
    private Boolean activo;
    private ProductoTipoVenta tipoVenta;
    private ProductoUnidadBase unidadBase;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private ProductoCategoriaResponseDto categoria;
    private ProductoMarcaResponseDto marca;

    public ProductoCrearResponseDto() {
    }

    public ProductoCrearResponseDto(Long id, String codigoBarras, String nombre, String descripcion, BigDecimal precioVenta, BigDecimal stockTotal, BigDecimal stockMinimoAlerta, Boolean activo, ProductoTipoVenta tipoVenta, ProductoUnidadBase unidadBase, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, ProductoCategoriaResponseDto categoria, ProductoMarcaResponseDto marca) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.stockTotal = stockTotal;
        this.stockMinimoAlerta = stockMinimoAlerta;
        this.activo = activo;
        this.tipoVenta = tipoVenta;
        this.unidadBase = unidadBase;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.categoria = categoria;
        this.marca = marca;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
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
