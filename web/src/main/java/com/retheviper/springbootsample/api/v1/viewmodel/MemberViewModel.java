package com.retheviper.springbootsample.api.v1.viewmodel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * Member view model class.
 *
 * @author retheviper
 */
@Data
public class MemberViewModel implements Serializable {

    /**
     * Member's ID(user ID)
     */
    private String uid;

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
