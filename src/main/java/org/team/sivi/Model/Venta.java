package org.team.sivi.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoVenta;
    private String nombreUsuario;
    private String rolUsuario;
    private String email;
    private LocalDateTime fechaVenta;
    private BigDecimal montoTotal;
    private BigDecimal descuentoTotal;
    private String metodoPago;
    private String estado;
    private BigDecimal gananciaVenta;
    private BigDecimal dineroRecibido;
    private BigDecimal cambio;
   @ManyToOne
    private Usuario usuario;
   @OneToMany(mappedBy = "venta",cascade = CascadeType.ALL)
    private List<DetalleVenta>listaDetallesVenta;

    public Venta() {
    }

    public Venta(Long id, String codigoVenta, String nombreUsuario, String rolUsuario, String email, LocalDateTime fechaVenta, BigDecimal montoTotal, BigDecimal descuentoTotal, String metodoPago, String estado, BigDecimal gananciaVenta, BigDecimal dineroRecibido, BigDecimal cambio, Usuario usuario, List<DetalleVenta> listaDetallesVenta) {
        this.id = id;
        this.codigoVenta = codigoVenta;
        this.nombreUsuario = nombreUsuario;
        this.rolUsuario = rolUsuario;
        this.email = email;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.descuentoTotal = descuentoTotal;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.gananciaVenta = gananciaVenta;
        this.dineroRecibido = dineroRecibido;
        this.cambio = cambio;
        this.usuario = usuario;
        this.listaDetallesVenta = listaDetallesVenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public BigDecimal getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(BigDecimal descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getGananciaVenta() {
        return gananciaVenta;
    }

    public void setGananciaVenta(BigDecimal gananciaVenta) {
        this.gananciaVenta = gananciaVenta;
    }

    public BigDecimal getDineroRecibido() {
        return dineroRecibido;
    }

    public void setDineroRecibido(BigDecimal dineroRecibido) {
        this.dineroRecibido = dineroRecibido;
    }

    public BigDecimal getCambio() {
        return cambio;
    }

    public void setCambio(BigDecimal cambio) {
        this.cambio = cambio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleVenta> getListaDetallesVenta() {
        return listaDetallesVenta;
    }

    public void setListaDetallesVenta(List<DetalleVenta> listaDetallesVenta) {
        this.listaDetallesVenta = listaDetallesVenta;
    }
}
