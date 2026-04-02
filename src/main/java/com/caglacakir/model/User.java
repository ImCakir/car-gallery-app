package com.caglacakir.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity  implements UserDetails {
    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;


    // Boş authority döndür
    @Override
    @Transient // // Bu metodun veritabanında bir sütun olmadığını belirtir
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

}

/*
1. Neden Bu Arayüze İhtiyacımız Var?
Sizin veritabanınızdaki User sınıfında alan adları farklı olabilir (örneğin: eposta, sifre, rumuz). Ancak Spring
Security, kimlik doğrulama yaparken hangi alanın şifre, hangi alanın kullanıcı adı olduğunu bilemez. UserDetails
arayüzünü implement ettiğinizde, Spring Security'ye şunu demiş olursunuz:

"Bak Spring, benim username alanım senin aradığın kullanıcı adıdır, password alanım da senin aradığın şifredir."

2. İçindeki Kritik Metodlar
Arayüzü implement ettiğinizde gelen metodların her birinin bir "güvenlik" görevi vardır:

getAuthorities(): Kullanıcının rollerini (ROLE_USER, ROLE_ADMIN gibi) döner. "Bu kullanıcı içeri girince ne yapabilir?"
sorusunun cevabıdır.

getPassword() ve getUsername(): Kimlik doğrulama (Login) sırasında karşılaştırma yapılacak bilgileri sağlar.

Durum Kontrolleri (Boolean):

isAccountNonExpired(): Hesap süresi dolmuş mu?

isAccountNonLocked(): Kullanıcı çok hatalı giriş yapıp kilitlenmiş mi?

isCredentialsNonExpired(): Şifre değişim zamanı gelmiş mi?

isEnabled(): Kullanıcı hesabı aktif mi (email onayı yapılmış mı)?

3. Nasıl Çalışır? (Akış)
Kullanıcı bir form üzerinden giriş yapar.

Spring Security, UserDetailsService (bu başka bir parçadır) üzerinden kullanıcıyı veritabanından çeker.

Veritabanından çektiğiniz nesne UserDetails tipinde olduğu için, Spring içindeki mekanizmalar doğrudan .getPassword()
veya .getAuthorities() metodlarını çağırarak kontrolü otomatik yapar.

Özetle
Eğer UserDetails kullanmasaydınız, her giriş işleminde şifreleri manuel karşılaştırmak, rolleri tek tek kontrol etmek
ve session yönetimini elinizle yapmak zorunda kalırdınız. Bu interface sayesinde kendi User entity'nizi Spring Security'nin
 yerli bir parçası haline getirmiş oluyorsunuz.
* */







