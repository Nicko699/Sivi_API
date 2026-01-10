package org.team.sivi.Dto.RefreshTokenDto;

public class RefreshTokenRenovarAccesTokenResponseDto {
    private String accessToken;
    private String tipoToken;

    public RefreshTokenRenovarAccesTokenResponseDto() {
    }

    public RefreshTokenRenovarAccesTokenResponseDto(String accessToken, String tipoToken) {
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
