package com.barisd.manager;

import com.barisd.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.barisd.constant.EndPoints.*;

/**
 * @FeignClient: Bu anotasyonla bir FeignClient tanımı yapılır.
 * Burada name değeri eşsiz olmalıdır.
 * url kısmına vereceğimiz adres, istek atılacak olan sınıfın requestmapping adresi olmalı.
 */

@FeignClient(name = "user-profile-manager", url="http://localhost:9091/api/v1/user", decode404 = true)
public interface IUserProfileManager {
    @PostMapping(SAVE)
    ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto);
}
