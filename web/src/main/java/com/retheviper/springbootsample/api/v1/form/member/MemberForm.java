package com.retheviper.springbootsample.api.v1.form.member;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Member create form class.
 *
 * @author retheviper
 */
@Data
public class MemberForm implements Serializable {

    /**
     * Member's ID(user ID)
     */
    @Size(min = 4, max = 16, message = "User name must be 4 to 16 characters")
    private String userId;

    /**
     * Member's name
     */
    @Size(min = 4, max = 16, message = "Name must be 4 to 16 characters")
    private String name;

    /**
     * Member's password
     */
    @Size(min = 4, max = 16, message = "Password must be 4 to 16 characters")
    private String password;

    /**
     * Member's new password
     */
    @Size(min = 4, max = 16, message = "Password must be 4 to 16 characters")
    private String newPassword;
}
