package com.barisd.controller;

import com.barisd.dto.request.UserProfileSaveRequestDto;
import com.barisd.repository.entity.UserProfile;
import com.barisd.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userprofile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping("/save")
    public ResponseEntity<UserProfile> save(@RequestBody UserProfileSaveRequestDto dto){
        UserProfile userProfile = userProfileService.save(dto.getUsername(), dto.getEmail());
        return ResponseEntity.ok(userProfile);
    }
}
