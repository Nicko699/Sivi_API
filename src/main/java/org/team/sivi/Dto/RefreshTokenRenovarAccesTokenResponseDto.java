package org.team.sivi.Dto;

public class RefreshTokenRenovarAccesTokenResponseDto {
    private String accessToken;
    private String tipoToken;
    private RefreshTokenResponseDto refreshToken;

    public RefreshTokenRenovarAccesTokenResponseDto() {
    }

    public RefreshTokenRenovarAccesTokenResponseDto(String accessToken, String tipoToken, RefreshTokenResponseDto refreshToken) {
        this.accessToken = accessToken;
        this.tipoToken = tipoToken;
        this.refreshToken = refreshToken;
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
