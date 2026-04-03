package com.caglacakir.controller.impl;

import com.caglacakir.controller.IRestAuthenticationController;
import com.caglacakir.controller.RestBaseController;
import com.caglacakir.controller.RootEntity;
import com.caglacakir.dto.AuthRequest;
import com.caglacakir.dto.DtoUser;
import com.caglacakir.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid  @RequestBody AuthRequest input) {
        return ok(authenticationService.register(input));
    }
}
