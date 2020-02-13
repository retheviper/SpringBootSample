package com.retheviper.springbootsample.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(unique = true, updatable = false, nullable = false)
    @Size(max = 16)
    private String memberId;

    @Column(nullable = false)
    private String realIdentity;

    @Column(nullable = false)
    private String password;

    @Column(updatable = false, nullable = false)
    private LocalDateTime joinedDate;
}
