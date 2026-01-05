package org.team.sivi.Dto.UsuarioDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.team.sivi.Model.Rol;

import java.util.List;
//Dto para recibir los campos del usuario editado
public class UsuarioEditarRequestDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotNull(message = "Activo no puede ser null")
    private Boolean activo;
    @NotEmpty(message = "La lista de roles no puede estar vacía")
    private List<Rol> listaRol;

    public UsuarioEditarRequestDto() {
    }

    public UsuarioEditarRequestDto(String nombre, Boolean activo, List<Rol> listaRol) {
        this.nombre = nombre;
        this.activo = activo;
        this.listaRol = listaRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }
}
