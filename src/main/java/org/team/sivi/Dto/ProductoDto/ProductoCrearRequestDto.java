package org.team.sivi.Dto.ProductoDto;
import jakarta.validation.constraints.*;
import org.team.sivi.Model.Enum.ProductoTipoVenta;
import org.team.sivi.Model.Enum.ProductoUnidadBase;
import java.math.BigDecimal;

public class ProductoCrearRequestDto {

   @Size(max = 50, message = "el código de barras no puede superar los 50 caracteres")
    private String codigoBarras;

   @NotBlank(message = "El campo nombre no puede estar vacío")
   @Size(max = 30, message = "El nombre no puede superar los 30 caracteres")
    private String nombre;

   @Size(max = 250, message = "La descripción no puede superar los 250 caracteres")
    private String descripcion;

   @NotNull(message = "El precio de la venta es obligatorio")
   @DecimalMin(value = "0.1",message = "El precio de la venta no puede ser negativo")
    private BigDecimal precioVenta;

   @NotNull(message = "El stock minimo es obligatorio")
   @DecimalMin(value = "0.0",message = "El stock minimo no puede estar negativo")
    private BigDecimal stockMinimoAlerta;

   @NotNull(message = "El tipo venta es obligatorio")
   private ProductoTipoVenta tipoVenta;

   @NotNull(message = "La categoria es obligatoria")
    private Long categoriaId;

   @NotNull(message ="La marca es obligatoria")
    private Long marcaId;

    public ProductoCrearRequestDto() {
    }

    public ProductoCrearRequestDto(String codigoBarras, String nombre, String descripcion, BigDecimal precioVenta, BigDecimal stockMinimoAlerta, ProductoTipoVenta tipoVenta, Long categoriaId, Long marcaId) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.stockMinimoAlerta = stockMinimoAlerta;
        this.tipoVenta = tipoVenta;
        this.categoriaId = categoriaId;
        this.marcaId = marcaId;
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

    public BigDecimal getStockMinimoAlerta() {
        return stockMinimoAlerta;
    }

    public void setStockMinimoAlerta(BigDecimal stockMinimoAlerta) {
        this.stockMinimoAlerta = stockMinimoAlerta;
    }

    public ProductoTipoVenta getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(ProductoTipoVenta tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }
}
