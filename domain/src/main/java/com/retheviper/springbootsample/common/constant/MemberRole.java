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
    public static final String ADMIN = "ROLE_ADMIN";

    /**
     * Role: User
     */
    public static final String USER = "ROLE_USER";

}
