package com.barisd.config.security;

import com.barisd.exception.ErrorType;
import com.barisd.exception.UserProfileServiceException;
import com.barisd.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    JwtUserDetails jwtUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("!!!  JwtTokenFilter devrede  !!!");

        //istekte gelen bearer tokeni yakalama:
        String bearerToken = request.getHeader("Authorization");
        if(Objects.nonNull(bearerToken) && bearerToken.startsWith("Bearer ") ) {
            String token = bearerToken.substring(7);
            // tokendan auth id alma:
            Optional<Long> authid = jwtTokenManager.decodeToken(token);

            // Eğer authid boş ise token yanlıştır:
            if (authid.isEmpty())
                throw new UserProfileServiceException(ErrorType.INVALID_TOKEN);

            UserDetails userDetails = jwtUserDetails.loadUserByAuthid(authid.get());

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            //tokeni springe aktarma kısmı:
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);

    }
}
