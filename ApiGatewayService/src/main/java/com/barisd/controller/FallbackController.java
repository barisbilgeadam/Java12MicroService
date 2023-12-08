package com.barisd.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/auth")
    public ResponseEntity<String> fallbackAuth(){
        /**
         * hata durumunda düşülen endpoint burası. Bu durumda yapılması istenen başka
         * işlemler burada yapılabilir. Loglama gibi..
         */
        return ResponseEntity.ok("Auth servisi şu an yanıt vermemektedir. Lütfen daha sonra tekrar deneyiniz.");
    }

    @GetMapping("/user")
    public ResponseEntity<String> fallbackUser(){
        return ResponseEntity.ok("UserProfile servisi şu an yanıt vermemektedir. Lütfen daha sonra tekrar deneyiniz.");
    }


}
