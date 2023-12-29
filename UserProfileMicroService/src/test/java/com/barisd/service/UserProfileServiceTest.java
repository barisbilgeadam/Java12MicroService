package com.barisd.service;

import com.barisd.dto.request.UserProfileUpdateRequestDto;
import com.barisd.repository.UserProfileRepository;
import com.barisd.repository.entity.UserProfile;
import com.barisd.utility.JwtTokenManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserProfileServiceTest {

    @InjectMocks
    private UserProfileService userProfileService;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private JwtTokenManager jwtTokenManager;

    @Test
    void updateProfileForEmpty() {
        when(jwtTokenManager.decodeToken(anyString())).thenReturn(Optional.of(1L));

        when(userProfileRepository.findOptionalByAuthid(anyLong())).thenReturn(Optional.empty());
        userProfileService.updateProfile(UserProfileUpdateRequestDto.builder().token("123").build());
    }
    @Test
    void updateProfileForPresent() {
        when(jwtTokenManager.decodeToken(anyString())).thenReturn(Optional.of(1L));
        UserProfile userProfile=new UserProfile();
        when(userProfileRepository.findOptionalByAuthid(anyLong())).thenReturn(Optional.of(userProfile));
        userProfileService.updateProfile(UserProfileUpdateRequestDto.builder().token("123").build());



    }
}