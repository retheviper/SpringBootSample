package com.retheviper.springbootsample.api.v1.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IdentityVerificationUtil {

    public static boolean isLoginedUser(final String username) {
        return Objects.equals(username, SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
