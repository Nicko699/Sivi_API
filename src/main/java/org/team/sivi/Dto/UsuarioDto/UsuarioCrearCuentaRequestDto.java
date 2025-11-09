package org.team.sivi.Dto.UsuarioDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
//Creamos un Dto para recibir la solicitud del usuario UsuarioCrearCuentaRequestDto
public class UsuarioCrearCuentaRequestDto {
    //Usamos las anotaciones de la libreria validacion para controlar los datos enviados por el usuario para que cumplan con las reglas definidas
    //por nuestro sistema
    @NotEmpty(message = "El nombre es obligatorio")
    private String nombre;
    @NotEmpty(message = "El correo electronico es obligatorio")
    @Email(message = "El correo electronico debe de tener un formato válido")
    private String correo;
    @NotEmpty(message = "La contraseña es obligatoria")
    private String password;
    @NotEmpty(message = "Ingresa por lo menos 1 rol")
    private List<Long> listaRol;

    public UsuarioCrearCuentaRequestDto() {
    }

    public UsuarioCrearCuentaRequestDto(String nombre, String correo, String password, List<Long> listaRol) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.listaRol = listaRol;
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

    public List<Long> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Long> listaRol) {
        this.listaRol = listaRol;
    }
}
