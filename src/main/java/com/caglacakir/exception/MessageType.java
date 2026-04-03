package com.caglacakir.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004", "Kayıt Bulunamadı.."),
    TOKEN_IS_EXPIRED("1005", "tokenın süresi bitmiştir.."),
    USERNAME_NOT_FOUND("1006", "Kullanıcı bulunamadı.."),
    GENERAL_EXCEPTION ("9999", "genel bir hata oluştu..");

    private final String code;

    private final String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
