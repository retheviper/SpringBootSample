package com.retheviper.springbootsample.web.viewmodel;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberViewModel implements Serializable {

    private String memberId;

    private String realIdentity;

    private LocalDate joinedDate;
}
