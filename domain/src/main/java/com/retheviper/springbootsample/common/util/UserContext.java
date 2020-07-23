package com.retheviper.springbootsample.common.util;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class for user context.
 *
 * @author retheviper
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserContext {

    /**
     * Check logined user equals to given user ID.
     *
     * @param userId user ID
     * @return result of check
     */
    public static boolean loginedUserMatches(final String userId) {
        return SecurityContextHolder.getContext().getAuthentication().getName().equals(userId);
    }
}
