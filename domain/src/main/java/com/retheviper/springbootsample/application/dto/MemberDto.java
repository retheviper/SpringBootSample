package com.retheviper.springbootsample.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Member DTO class.
 *
 * @author retheviper
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto implements Serializable {

    /**
     * Member's ID(user ID)
     */
    private String uid;

    /**
     * Member's name
     */
    private String name;

    /**
     * Member's password
     */
    private String password;

    /**
     * Member's new password
     */
    private String newPassword;

    /**
     * Created date of member
     */
    private LocalDateTime createdDate;

    /**
     * Last modified date of member
     */
    private LocalDateTime lastModifiedDate;

    /**
     * Is member's account not expired
     */
    private boolean accountNonExpired;

    /**
     * Is member's account not locked
     */
    private boolean accountNonLocked;

    /**
     * Is member's credential not expired
     */
    private boolean credentialsNonExpired;

    /**
     * Is member's account enabled
     */
    private boolean enabled;

    /**
     * Member's role
     */
    private List<String> roles;

    /**
     * Member's authorities
     */
    private List<String> authorities;
}
