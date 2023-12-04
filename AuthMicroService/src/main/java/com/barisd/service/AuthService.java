package com.barisd.service;

import com.barisd.dto.request.DoLoginRequestDto;
import com.barisd.dto.request.RegisterRequestDto;
import com.barisd.exception.AuthServiceException;
import com.barisd.exception.ErrorType;
import com.barisd.mapper.IAuthMapper;
import com.barisd.repository.IAuthRepository;
import com.barisd.repository.entity.Auth;
import com.barisd.utility.JwtTokenManager;
import com.barisd.utility.ServiceManager;
import com.barisd.utility.TokenManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final JwtTokenManager jwtTokenManager;
    public AuthService(IAuthRepository repository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public Optional<Auth> findOptionalByEmailAndPassword(String email, String password){
        return repository.findOptionalByEmailAndPassword(email,password);
    }

    public Auth register(RegisterRequestDto dto) {
        if(repository.existsByEmail(dto.getEmail()))
            throw new AuthServiceException(ErrorType.REGISTER_EMAIL_ALREADY_EXISTS);
        Auth auth=IAuthMapper.INSTANCE.registerRequestDtoToAuth(dto);
        return save(auth);
    }

    /**
     * Email ve şifre kullanılarak login işlemi yaptırılır.
     * Bu işlem başarısız ise hata fırlatalım.
     * Bu işlem başarılı ise ona gidip özel bir kimlik vereceğiz. (TOKEN)
     * @param dto
     * @return
     */

    public String doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth = repository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if(auth.isEmpty()) throw new AuthServiceException(ErrorType.DOLOGIN_EMAILORPASSWORD_NOT_EXISTS);
        return jwtTokenManager.createToken(auth.get().getId()).get();
    }

    public List<Auth> findAll(String token) {
        Optional<Long> idFromToken;
        try {
            idFromToken = jwtTokenManager.decodeToken(token);
        } catch (Exception e) {
            throw new AuthServiceException(ErrorType.INVALID_TOKEN_FORMAT);
        }

        if(!repository.existsById(idFromToken.get()))
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);

        return findAll();

    }
}
