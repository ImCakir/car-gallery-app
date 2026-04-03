package com.caglacakir.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

    }
}

/*
Burada şunu yaptım. Bizim uygulamamız 403 fırlatıyor idi.(yani erişim yok). Ancak biz 403 fırlatsın istemiyoruz.
401 fırlatsın ve front endci token suresı doldugunu anlasın. bunu aktıve edıcez.
securıtyconfig --> 403 forbiden fırlatıyor.
biz authEntryPoint'i handle edicez, geriye 401 --> SC_UNAUTHORIZED dönecek :)
 */