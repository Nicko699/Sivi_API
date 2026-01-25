package org.team.sivi.Model;

import jakarta.persistence.*;
import org.team.sivi.Model.Enum.ProductoTipoVenta;
import org.team.sivi.Model.Enum.ProductoUnidadBase;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoBarras;
    private String nombre;
    private String descripcion;
    // Precio de venta: por unidad si tipoVenta=UNIDAD, por kg si tipoVenta=PESO
    private BigDecimal precioVenta;
    private BigDecimal stockTotal;
    private BigDecimal stockMinimoAlerta;
    private Boolean bajoStock;
    private Boolean activo;
    private Boolean softDelete;
    @Enumerated(EnumType.STRING)
    // Tipo de venta: UNIDAD (no se pesa) o PESO (siempre se pesa)
    private ProductoTipoVenta tipoVenta;
    @Enumerated(EnumType.STRING)
    //unidad base del stock, unidad= unidad, peso=kg
    private ProductoUnidadBase unidadBase;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Marca marca;

    @OneToMany(mappedBy = "producto",cascade = CascadeType.ALL)
    private List<Lote>listaLotes;

    public Producto() {
    }

    public Producto(Long id, String codigoBarras, String nombre, String descripcion, BigDecimal precioVenta, BigDecimal stockTotal, BigDecimal stockMinimoAlerta, Boolean bajoStock, Boolean activo, Boolean softDelete, ProductoTipoVenta tipoVenta, ProductoUnidadBase unidadBase, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Categoria categoria, Marca marca, List<Lote> listaLotes) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.stockTotal = stockTotal;
        this.stockMinimoAlerta = stockMinimoAlerta;
        this.bajoStock = bajoStock;
        this.activo = activo;
        this.softDelete = softDelete;
        this.tipoVenta = tipoVenta;
        this.unidadBase = unidadBase;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.categoria = categoria;
        this.marca = marca;
        this.listaLotes = listaLotes;
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

    public Boolean getBajoStock() {
        return bajoStock;
    }

    public void setBajoStock(Boolean bajoStock) {
        this.bajoStock = bajoStock;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(Boolean softDelete) {
        this.softDelete = softDelete;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }
}
