package org.team.sivi.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoBarras;
    private String nombreProducto;
    private BigDecimal precioUnitario;
    private String unidadBase;
    private String nombreCategoria;
    private String nombreMarca;
    private BigDecimal cantidad;
    private BigDecimal subTotal;
    private LocalDateTime fechaRegistro;
   @ManyToOne
    private Venta venta;
    @ManyToOne
    private Producto producto;
    @JoinTable(name = "detalleVenta_lote",joinColumns = @JoinColumn(name = "detalleVenta_id"),
    inverseJoinColumns = @JoinColumn(name = "lote_id"))
   @ManyToMany
    private List<Lote> listaLotes;

    public DetalleVenta() {
    }

    public DetalleVenta(Long id, String codigoBarras, String nombreProducto, BigDecimal precioUnitario, String unidadBase, String nombreCategoria, String nombreMarca, BigDecimal cantidad, BigDecimal subTotal, LocalDateTime fechaRegistro, Venta venta, Producto producto, List<Lote> listaLotes) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.unidadBase = unidadBase;
        this.nombreCategoria = nombreCategoria;
        this.nombreMarca = nombreMarca;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.fechaRegistro = fechaRegistro;
        this.venta = venta;
        this.producto = producto;
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

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getUnidadBase() {
        return unidadBase;
    }

    public void setUnidadBase(String unidadBase) {
        this.unidadBase = unidadBase;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(List<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }
}
