package com.caglacakir.service;

import com.caglacakir.dto.AuthRequest;
import com.caglacakir.dto.DtoUser;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);
}
