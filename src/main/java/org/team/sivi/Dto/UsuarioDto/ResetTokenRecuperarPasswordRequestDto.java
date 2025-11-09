package org.team.sivi.Dto.UsuarioDto;

import jakarta.validation.constraints.NotEmpty;

public class ResetTokenRecuperarPasswordRequestDto {

    @NotEmpty(message = "El correo es obligatorio ")
   private String correo;

    public ResetTokenRecuperarPasswordRequestDto(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
