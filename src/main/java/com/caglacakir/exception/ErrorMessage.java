package com.caglacakir.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private MessageType messageType; // Enum'dır hangi tip hata olduğunu saklar

    private String ofStatic; // // Hata ile ilgili ekstra bilgi




    public String prepareErrorMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append(messageType.getMessage());
        if(this.ofStatic!=null) {
            builder.append(" : " + ofStatic);
        }
        return builder.toString();
    }
}

/*
1️⃣ Sınıfın amacı

ErrorMessage sınıfı, hata mesajlarını standart ve dinamik bir şekilde oluşturmak için kullanılır.
	•	API çağrısı sırasında veya uygulamada bir hata oluştuğunda, bu sınıf ile mesaj oluşturursun.
	•	Hata mesajları enum (MessageType) üzerinden yönetildiği için, her hatanın kodu ve mesajı standart ve tek yerde tutulur.
2️⃣ ofStatic değişkeni ne için?

ofStatic değişkeni hata ile ilgili ek bilgi eklemek için kullanılır.

Mesela:
	•	Kayıt bulunamadığında, hangi tablo veya kullanıcı ile ilgili olduğunu belirtmek isteyebilirsin.

3️⃣ Avantajı
	•	Hataları tip güvenli ve standart şekilde yönetirsin.
	•	Kod tekrarını önler.
	•	Mesaj formatını merkezi bir yerden değiştirebilirsin.
	•	API için JSON çıktısı üretirken kolayca kullanabilirsin:

{
  "code": "1004",
  "message": "Kayıt Bulunamadı.. Kullanıcı"
}
*/
