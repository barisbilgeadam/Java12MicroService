package com.barisd.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {
    String secretKey="omDB5Y9384OSJOKDjSeYfQV08UhbpJ9q1aujEjyxe9jg2FwXFz";
    Long expTime=1000L*60*15;//15dk lık bir süre.
    //1. token generate et. (üret)

    /**
     * Claim objesi içine yazacağınız bilgiler herkes tarafından görünebilecektir.
     * O yüzden, email-password gibi bilgiler burada olmamalıdır.
     * @param id
     * @return
     */
    public Optional<String> createToken(Long id){
        try {
          return Optional.of(JWT.create()
                    .withAudience()
                    .withClaim("id", id)
                    .withClaim("service", "AuthMicroService")
                    .withClaim("ders", "Java JWT")
                    .withClaim("grup", "Java 12")
                    .withIssuer("Java12") //jwt token oluşturan
                    .withIssuedAt(new Date(System.currentTimeMillis())) //jwt token oluşturma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis() + expTime))
                    .sign(Algorithm.HMAC512(secretKey)));
        }
        catch (Exception e){
            return Optional.empty();
        }

    }

    //2. token verify et. (doğrula)
    public Boolean verifyToken(String token){
        return false;
    }

    //3. token decode et. (bilgi çıkarımı yap)
    public Long decodeToken(String token){
        return 0L;
    }
}
