package com.barisd.controller;

import static com.barisd.constant.EndPoints.*;

import com.barisd.dto.request.GetProfileFromTokenRequestDto;
import com.barisd.dto.request.UserProfileSaveRequestDto;
import com.barisd.dto.request.UserProfileUpdateRequestDto;
import com.barisd.dto.response.UserProfileResponseDto;
import com.barisd.repository.entity.UserProfile;
import com.barisd.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ROOT+USER)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto){
        return ResponseEntity.ok(userProfileService.saveDto(dto));
    }

    @PostMapping(GETFROMTOKEN)
    public ResponseEntity<UserProfileResponseDto> getProfileFromToken(@RequestBody GetProfileFromTokenRequestDto dto){
        return  ResponseEntity.ok(userProfileService.getProfileFromToken(dto));
    }

    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> updateProfile(@RequestBody UserProfileUpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateProfile(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
}
