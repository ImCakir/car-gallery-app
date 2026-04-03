package com.caglacakir.service;

import com.caglacakir.dto.AuthRequest;
import com.caglacakir.dto.AuthResponse;
import com.caglacakir.dto.DtoUser;
import com.caglacakir.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
}
