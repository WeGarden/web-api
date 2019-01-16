package com.wegarden.api.users;

public class JwtAuthenticationResponse {
    private Long userId;
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(Long userId, String accessToken) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
