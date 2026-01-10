package org.team.sivi.Dto.UsuarioDto;

import org.team.sivi.Dto.RolResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public class UsuarioListaResponseDto {
    private Long id;
    private String nombre;
    private String correo;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private List<RolResponseDto> listaRol;

    public UsuarioListaResponseDto() {
    }

    public UsuarioListaResponseDto(Long id, String nombre, String correo, boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, List<RolResponseDto> listaRol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.listaRol = listaRol;
    }

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

    public List<RolResponseDto> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<RolResponseDto> listaRol) {
        this.listaRol = listaRol;
    }
}
