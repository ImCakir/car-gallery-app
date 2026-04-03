package com.caglacakir.controller;

import com.caglacakir.dto.AuthRequest;
import com.caglacakir.dto.AuthResponse;
import com.caglacakir.dto.DtoUser;
import com.caglacakir.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {
    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
