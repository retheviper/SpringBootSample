package com.retheviper.springbootsample.api.v1.viewmodel.member;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Member view model class.
 *
 * @author retheviper
 */
@Data
public class MemberViewModel implements Serializable {

    /**
     * Member ID
     */
    private long id;

    /**
     * Member's user ID
     */
    private String userId;

    /**
     * Member's name
     */
    private String name;

    /**
     * Created date of member
     */
    private LocalDateTime createdDate;

    /**
     * Member's role
     */
    private List<String> roles;
}
