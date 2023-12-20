package com.barisd.config.security;

import com.barisd.exception.ErrorType;
import com.barisd.repository.entity.UserProfile;
import com.barisd.service.UserProfileService;
import com.barisd.service.YetkiService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService {
    private final UserProfileService userProfileService;
    private final YetkiService yetkiService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByAuthid(Long authId){

        Optional<UserProfile> userProfile = userProfileService.findByAuthid(authId);
        if(userProfile.isEmpty())
            return null;

        List<GrantedAuthority> authorities=new ArrayList<>();

        yetkiService.findAllByUserprofileid(userProfile.get().getId()).forEach(
                yetki->{
                    authorities.add(new SimpleGrantedAuthority(yetki.getYetki()));
                }
        );

        return User.builder()
                .username(userProfile.get().getUsername())
                .password("")
                .accountExpired(false)
                .accountLocked(false)
                .authorities(authorities)
                .build();
    }
}
