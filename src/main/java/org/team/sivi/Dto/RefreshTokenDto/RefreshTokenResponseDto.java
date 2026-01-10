package org.team.sivi.Dto.RefreshTokenDto;

public class RefreshTokenResponseDto {

    private String refreshTokenId;
    private String refreshToken;

    public RefreshTokenResponseDto() {
    }

    public RefreshTokenResponseDto(String refreshTokenId, String refreshToken) {
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
