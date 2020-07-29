package com.retheviper.springbootsample.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Class for member role constants.
 *
 * @author retheviper
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRole {

    /**
     * Role: Admin
     */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * Admin
     */
    public static final String ADMIN = "ADMIN";

    /**
     * Role: User
     */
    public static final String ROLE_USER = "ROLE_USER";

    /**
     * User
     */
    public static final String USER = "USER";

}
