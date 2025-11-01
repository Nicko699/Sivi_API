package org.team.sivi.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
//Dto para recibir los datos del usuario al iniciar sesion
public class UsuarioIniciarSesionRequestDto {
    //Usamos las anotaciones de la libreria validacion para controlar los datos enviados por el usuario para que cumplan con las reglas definidas
    //por nuestro sistema
    @NotEmpty(message = "El correo electronico es obligatorio")
    @Email(message = "El correo electronico debe de estar en un formato válido")
    private String correo;
    @NotEmpty(message = "La contraseña es obligatoria")
    private String password;

    public UsuarioIniciarSesionRequestDto() {
    }

    public UsuarioIniciarSesionRequestDto(String correo, String password) {
        this.correo = correo;
        this.password = password;
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
}
