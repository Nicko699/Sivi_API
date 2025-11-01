package org.team.sivi.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//Creamos la clase usuario
//Indicamos con @Entity que va a ser una entidad en la bd
@Entity
public class Usuario {
    //Atributos de la clase
    //Indicamos que el atributo id va a ser nuestro primary key en la bd y que se va a generar automaticamente y autoIncrementandose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String password;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    //Indicamos que Usuario va a tener una relacion N a N con Rol
    //Creamos una tabla intermedia donde se guardará los usuarios relacionados con los roles
    @JoinTable(name = "usuario_rol",joinColumns = @JoinColumn(name = "usuario_Id"),inverseJoinColumns = @JoinColumn(name = "rol_id"))
    @ManyToMany()
    private List<Rol>listaRol;
   //Indicamos que va a tener una relacion 1 a N con refreshToken
    //Tambien especificamos que refreshToken va a ser el dueño d ela relacion
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<RefreshToken>listaRefreshTokens;

    //Creamos el constructor vacío
    public Usuario() {
    }

     //Creamos el constructor con todos sus atributos
    public Usuario(Long id, String nombre, String correo, String password, boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, List<Rol> listaRol, List<RefreshToken> listaRefreshTokens) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.listaRol = listaRol;
        this.listaRefreshTokens = listaRefreshTokens;
    }

    //Creamos los getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public List<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public List<RefreshToken> getListaRefreshTokens() {
        return listaRefreshTokens;
    }

    public void setListaRefreshTokens(List<RefreshToken> listaRefreshTokens) {
        this.listaRefreshTokens = listaRefreshTokens;
    }
}
