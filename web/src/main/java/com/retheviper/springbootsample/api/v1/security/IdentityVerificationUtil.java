package com.retheviper.springbootsample.api.v1.security;

import com.retheviper.springbootsample.application.service.member.UserDetailsServiceImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IdentityVerificationUtil {

    private final UserDetailsService service;

    public static boolean isLoginedUser(final String username) {
        return Objects.equals(username, SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
