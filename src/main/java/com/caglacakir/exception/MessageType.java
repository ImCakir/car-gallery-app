package com.caglacakir.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004", "Kayıt Bulunamadı.."),
    TOKEN_IS_EXPIRED("1005", "tokenın süresi bitmiştir.."),
    USERNAME_NOT_FOUND("1006", "Kullanıcı bulunamadı.."),
    USERNAME_OR_PASSWORD_INVALID("1007", "kullanıcı adı veya şifre hatalı"),
    REFRESH_TOKEN_NOT_FOUND("1008", "Refresh token bulunamadı.."),
    REFRESH_TOKEN_IS_EXPIRED("1009", "refreshtokenın süresi bitmiştir"),
    GENERAL_EXCEPTION ("9999", "genel bir hata oluştu..");

    private final String code;

    private final String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
