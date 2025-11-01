package org.team.sivi.Dto;
import java.util.List;
//Creamos un dto para mandar la respuesta del backed al usuario UsuarioCrearCuentaResponseDto
public class UsuarioCrearCuentaResponseDto {

    private Long id;
    private String nombre;
    private String correo;
    private List<RolResponseDto> listaRol;

    public UsuarioCrearCuentaResponseDto() {
    }

    public UsuarioCrearCuentaResponseDto(Long id, String nombre, String correo, List<RolResponseDto> listaRol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
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

    public List<RolResponseDto> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<RolResponseDto> listaRol) {
        this.listaRol = listaRol;
    }
}
