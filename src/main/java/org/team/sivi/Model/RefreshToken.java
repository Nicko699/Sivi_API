package org.team.sivi.Model;

import jakarta.persistence.*;

import java.time.Instant;
//Creamos la clase RefreshToken
//Indicamos con @Entity que va a ser una entidad en la bd
@Entity
public class RefreshToken {
    //Atributos de la clase
    //Indicamos que el atributo id va a ser nuestro primary key en la bd y que se va a generar automaticamente y autoIncrementandose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refreshToken;
    private String refreshTokenEncript;
    private Instant fechaCreacion;
    private Instant fechaExpiracion;
    private boolean activo;
    //Indicamos que refreshToken va a tener una relacion N a 1 con usuario
    @ManyToOne
    private Usuario usuario;
    //Creamos el constructor vacío
    public RefreshToken() {
    }
    //Creamos el constructor con todos sus atributos
    public RefreshToken(Long id, String refreshToken, String refreshTokenEncript, Instant fechaCreacion, Instant fechaExpiracion, boolean activo, Usuario usuario) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.refreshTokenEncript = refreshTokenEncript;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.activo = activo;
        this.usuario = usuario;
    }

    //Creamos los getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenEncript() {
        return refreshTokenEncript;
    }

    public void setRefreshTokenEncript(String refreshTokenEncript) {
        this.refreshTokenEncript = refreshTokenEncript;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Instant getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Instant fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
