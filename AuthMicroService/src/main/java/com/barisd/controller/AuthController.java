package com.barisd.controller;

import static com.barisd.constant.EndPoints.*;

import com.barisd.dto.request.DoLoginRequestDto;
import com.barisd.dto.request.RegisterRequestDto;
import com.barisd.exception.AuthServiceException;
import com.barisd.exception.ErrorType;
import com.barisd.repository.entity.Auth;
import com.barisd.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ROOT+AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * 1. Register İşlemi:
     */
    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@RequestBody @Valid RegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(ErrorType.REGISTER_PASSWORD_MISMATCH);

        return ResponseEntity.ok( authService.register(dto));
    }

    /**
     * 2. Login işlemi:
     */
    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody DoLoginRequestDto dto){

        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Auth>> findAll(String token){
        return ResponseEntity.ok(authService.findAll(token));
    }

    /**
     * ApiGateway Test Endpointi
     */

    @GetMapping("/message")
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("Auth Service get Message erişim sağlandı.");
    }
}
