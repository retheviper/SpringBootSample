package com.retheviper.springbootsample.api.v1.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Member update form class.
 *
 * @author retheviper
 */
@Data
public class UpdateMemberForm implements Serializable {

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
