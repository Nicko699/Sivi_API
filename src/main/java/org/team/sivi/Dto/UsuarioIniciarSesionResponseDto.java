package org.team.sivi.Dto;
//Dto para dar respuesta al inicio de sesion
public class UsuarioIniciarSesionResponseDto {

    private String mensaje;
    private String accessToken;
    private String tipoToken;
    private RefreshTokenResponseDto refreshToken;

    public UsuarioIniciarSesionResponseDto() {
    }

    public UsuarioIniciarSesionResponseDto(String mensaje, String accessToken, String tipoToken, RefreshTokenResponseDto refreshToken) {
        this.mensaje = mensaje;
        this.accessToken = accessToken;
        this.tipoToken = tipoToken;
        this.refreshToken = refreshToken;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    public RefreshTokenResponseDto getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshTokenResponseDto refreshToken) {
        this.refreshToken = refreshToken;
    }
}
