package org.team.sivi.Dto.ResetTokenDto;

import jakarta.validation.constraints.NotBlank;

public class ResetTokenRecuperarPasswordRequestDto {

    @NotBlank(message = "El correo es obligatorio ")
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
