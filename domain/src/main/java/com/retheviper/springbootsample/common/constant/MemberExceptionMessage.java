package com.retheviper.springbootsample.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberExceptionMessage {

    /** Data is null */
    E000("Non data Exists"),

    /** Password is incorrect */
    E001("Password Unmatch"),

    /** Password did not changed */
    E002("Password did not changed"),

    /** Password not inputed */
    E003("Password did not inputed"),

    /** Member ID already exists */
    E004("ID already exists"),

    /** Member ID does not exists */
    E005("ID does not exists");

    private final String message;
}
