package com.barisd.service;

import com.barisd.repository.UserProfileRepository;
import com.barisd.repository.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository repository;

    public UserProfile save(String username, String email){
       return repository.save(UserProfile.builder()
                        .username(username)
                        .email(email)
                .build());
    }
}
