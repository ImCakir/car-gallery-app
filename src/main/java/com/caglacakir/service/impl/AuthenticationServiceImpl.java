package com.caglacakir.service.impl;

import com.caglacakir.dto.AuthRequest;
import com.caglacakir.dto.AuthResponse;
import com.caglacakir.dto.DtoUser;
import com.caglacakir.dto.RefreshTokenRequest;
import com.caglacakir.exception.BaseException;
import com.caglacakir.exception.ErrorMessage;
import com.caglacakir.exception.MessageType;
import com.caglacakir.jwt.JWTService;
import com.caglacakir.model.RefreshToken;
import com.caglacakir.model.User;
import com.caglacakir.repository.RefreshTokenRepository;
import com.caglacakir.repository.UserRepository;
import com.caglacakir.service.IAuthenticationService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository; // db erişimi

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; // şifre hashlme

    @Autowired
    private AuthenticationProvider authenticationProvider; // şifre kontrolu

    @Autowired
    private RefreshTokenRepository refreshTokenRepository; // ref.Token db ye kaydet

    @Autowired
    private JWTService jwtService; // Token oluşturma

    private User createUser(AuthRequest input) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(input.getPassword()));

        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);

        return refreshToken;
    }


    @Override
    public DtoUser register(AuthRequest input) {
        DtoUser dtoUser = new DtoUser();

        User savedUser = userRepository.save(createUser(input));

        BeanUtils.copyProperties(savedUser, dtoUser);

        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {
        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optUser = userRepository.findByUsername(input.getUsername());
            String accessToken = jwtService.generateToken(optUser.get());
            RefreshToken savedRefreshToken= refreshTokenRepository.save(createRefreshToken(optUser.get()));

            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

    }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID,e.getMessage() ));

        }
    }




    public boolean isValidRefreshToken(Date expireDate){
        return new Date().before(expireDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest input) {
       Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
       if(optRefreshToken.isEmpty()){
           throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));

       }
       if(!isValidRefreshToken(optRefreshToken.get().getExpiredDate())){
           throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, input.getRefreshToken()));

       }
        User user = optRefreshToken.get().getUser();
       String accessToken = jwtService.generateToken(user);
       RefreshToken savedRefreshToken =refreshTokenRepository.save( createRefreshToken(user));


        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }
}
