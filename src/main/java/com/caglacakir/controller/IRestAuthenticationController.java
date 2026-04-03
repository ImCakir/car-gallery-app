package com.caglacakir.controller;

import com.caglacakir.dto.AuthRequest;
import com.caglacakir.dto.DtoUser;

public interface IRestAuthenticationController {
    public RootEntity<DtoUser> register(AuthRequest input);
}
