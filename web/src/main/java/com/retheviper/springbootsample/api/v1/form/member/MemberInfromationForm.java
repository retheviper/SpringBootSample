package com.retheviper.springbootsample.api.v1.form.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class MemberInfromationForm implements Serializable {

    @NotEmpty
    @Email
    private String email;

    private long boardId;
}
