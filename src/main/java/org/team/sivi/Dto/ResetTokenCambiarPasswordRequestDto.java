package org.team.sivi.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ResetTokenCambiarPasswordRequestDto {

    @NotBlank(message = "El resetTokenI es obligatorio")
    private String resetTokenId;
    @NotBlank(message = "El resetToken es obligatorio")
    private String resetToken;
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 10, max = 64, message = "La contraseña debe tener entre 10 y 64 caracteres")
    private String password;

    public ResetTokenCambiarPasswordRequestDto() {
    }

    public ResetTokenCambiarPasswordRequestDto(String resetTokenId, String resetToken, String password) {
        this.resetTokenId = resetTokenId;
        this.resetToken = resetToken;
        this.password = password;
    }

    public String getResetTokenId() {
        return resetTokenId;
    }

    public void setResetTokenId(String resetTokenId) {
        this.resetTokenId = resetTokenId;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
