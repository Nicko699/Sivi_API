package org.team.sivi.Dto.LoteDto;
import org.team.sivi.Dto.ProductoDto.ProductoNombreCategoría.ProductoCategoriaResponseDto;
import org.team.sivi.Dto.ProductoDto.ProductoNombreMarca.ProductoMarcaResponseDto;
import org.team.sivi.Model.Enum.ProductoTipoVenta;
import org.team.sivi.Model.Enum.ProductoUnidadBase;

import java.math.BigDecimal;

public class LoteProductoListarResponseDto {

    private Long id;
    private String codigoBarras;
    private String nombre;
    private String descripcion;
    private ProductoTipoVenta tipoVenta;
    private BigDecimal precioVenta;
    private ProductoUnidadBase unidadBase;
    private ProductoCategoriaResponseDto categoria;
    private ProductoMarcaResponseDto marca;

    public LoteProductoListarResponseDto() {
    }

    public LoteProductoListarResponseDto(Long id, String codigoBarras, String nombre, String descripcion, ProductoTipoVenta tipoVenta, BigDecimal precioVenta, ProductoUnidadBase unidadBase, ProductoCategoriaResponseDto categoria, ProductoMarcaResponseDto marca) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoVenta = tipoVenta;
        this.precioVenta = precioVenta;
        this.unidadBase = unidadBase;
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

    public ProductoTipoVenta getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(ProductoTipoVenta tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
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
