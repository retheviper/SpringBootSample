package com.retheviper.springbootsample.api.v1.form.member;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberInfromationForm implements Serializable {

    @NotEmpty
    @Email
    private String email;

    private long boardId;
}
