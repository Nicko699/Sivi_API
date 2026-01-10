package org.team.sivi.Dto.UsuarioDto;

//Dto para dar respuesta al inicio de sesion
public class UsuarioIniciarSesionResponseDto {

    private String accessToken;
    private String tipoToken;


    public UsuarioIniciarSesionResponseDto() {
    }

    public UsuarioIniciarSesionResponseDto(String accessToken, String tipoToken) {
        this.accessToken = accessToken;
        this.tipoToken = tipoToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }
}
