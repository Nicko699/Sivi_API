package org.team.sivi.Dto;

import jakarta.validation.constraints.NotEmpty;

public class RefreshTokenRenovarAccesTokenRequestDto {

    @NotEmpty(message = "El id del refresh token no puede estar vacío")
    private String refreshTokenId;
    @NotEmpty(message = "El refresh token no puede estar vacío")
    private String refreshToken;

    public RefreshTokenRenovarAccesTokenRequestDto() {
    }

    public RefreshTokenRenovarAccesTokenRequestDto(String refreshTokenId, String refreshToken) {
        this.refreshTokenId = refreshTokenId;
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
