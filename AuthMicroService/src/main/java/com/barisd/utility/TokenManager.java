package com.barisd.utility;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
    //1. token üretelim.

    /**
     * 5 => authtoken:5
     * @param id
     * @return
     */
    public String createToken(Long id){
        return "authtoken:"+id;
    }
    /*
    authtoken:5 => 5
     */
    //2.üretilen tokendan bilgi çıkarımı yap.
    public Long getIdFromToken(String token){
        String[] split = token.split(":");
        return Long.parseLong(split[1]);
    }
}
