package com.barisd.service;

import com.barisd.dto.request.DoLoginRequestDto;
import com.barisd.dto.request.RegisterRequestDto;
import com.barisd.exception.AuthServiceException;
import com.barisd.exception.ErrorType;
import com.barisd.manager.IUserProfileManager;
import com.barisd.mapper.IAuthMapper;
import com.barisd.rabbitmq.model.SaveAuthModel;
import com.barisd.rabbitmq.producer.CreateUserProducer;
import com.barisd.repository.IAuthRepository;
import com.barisd.repository.entity.Auth;
import com.barisd.utility.JwtTokenManager;
import com.barisd.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final IUserProfileManager iUserProfileManager;
    private final CreateUserProducer createUserProducer;
    /**
     * Oluşan hataları liste şeklinde tutup geri dönmek istersek:
     */

    public AuthService(IAuthRepository repository, JwtTokenManager jwtTokenManager, IUserProfileManager iUserProfileManager, CreateUserProducer createUserProducer) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
        this.iUserProfileManager = iUserProfileManager;
        this.createUserProducer = createUserProducer;
    }

    public Optional<Auth> findOptionalByEmailAndPassword(String email, String password){
        return repository.findOptionalByEmailAndPassword(email,password);
    }

    public Auth register(RegisterRequestDto dto) {
        List<ErrorType> errorTypes = new ArrayList<>();
        if(repository.existsByEmail(dto.getEmail()))
            errorTypes.add(ErrorType.REGISTER_EMAIL_ALREADY_EXISTS);

        if (!errorTypes.isEmpty()) {
            throw new AuthServiceException(errorTypes);
        }
        Auth auth=IAuthMapper.INSTANCE.registerRequestDtoToAuth(dto);
        save(auth);
       // iUserProfileManager.save(IAuthMapper.INSTANCE.fromAuth(auth)); // OpenFeign ile UserProfileService'e mesaj gönderme.
        createUserProducer.convertAndSend(SaveAuthModel.builder()
                        .authid(auth.getId())
                        .email(auth.getEmail())
                        .username(auth.getUsername())
                .build());


        return auth;
    }

    /**
     * Email ve şifre kullanılarak login işlemi yaptırılır.
     * Bu işlem başarısız ise hata fırlatalım.
     * Bu işlem başarılı ise ona gidip özel bir kimlik vereceğiz. (TOKEN)
     * @param dto
     * @return
     */

    public String doLogin(DoLoginRequestDto dto) {
        List<ErrorType> errorTypes = new ArrayList<>();
        Optional<Auth> auth = repository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if(auth.isEmpty())
            errorTypes.add(ErrorType.DOLOGIN_EMAILORPASSWORD_NOT_EXISTS);

        if (!errorTypes.isEmpty()) {
            throw new AuthServiceException(errorTypes);
        }
        return jwtTokenManager.createToken(auth.get().getId()).get();
    }

    public List<Auth> findAll(String token) {
        List<ErrorType> errorTypes = new ArrayList<>();
        Optional<Long> idFromToken;
        try {
            idFromToken = jwtTokenManager.decodeToken(token);
        } catch (Exception e) {
            errorTypes.add(ErrorType.INVALID_TOKEN_FORMAT);
            throw new AuthServiceException(ErrorType.INVALID_TOKEN_FORMAT);
        }
        if(!repository.existsById(idFromToken.get()))
            errorTypes.add(ErrorType.INVALID_TOKEN);
        if (!errorTypes.isEmpty()) {
            throw new AuthServiceException(errorTypes);
        }
        return findAll();

    }
}
