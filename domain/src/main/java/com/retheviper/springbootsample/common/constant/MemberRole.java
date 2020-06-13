package com.retheviper.springbootsample.common.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for member role.
 *
 * @author retheviper
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MemberRole {

    /**
     * Role: Admin
     */
    ADMIN("ROLE_ADMIN", "ADMIN"),

    /**
     * Role: User
     */
    USER("ROLE_USER", "USER");

    /**
     * Role value
     */
    private final String role;

    /**
     * Role name
     */
    private final String roleName;

}
