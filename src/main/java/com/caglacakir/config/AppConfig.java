package com.caglacakir.config;

import com.caglacakir.exception.BaseException;
import com.caglacakir.exception.ErrorMessage;
import com.caglacakir.exception.MessageType;
import com.caglacakir.model.User;
import com.caglacakir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class AppConfig {

    @Autowired
    private  UserRepository userRepository;


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            //Aldığı username e göre db' ye gidip usurname i  arıyor. bulursa usurdetails tipinde dönüyr
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               Optional<User> optional = userRepository.findByUsername(username);
               if(optional.isEmpty()) {
                    throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, username));
               }
                return optional.get();

            }

        };

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    // Şifreleri criptolamak için :)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


// DaoAuthentication provider username i alır. şifreyi alıyor. verıtabanını alıyor. Ekrandan gelen ile db de ki ni karsılastırıyr.
