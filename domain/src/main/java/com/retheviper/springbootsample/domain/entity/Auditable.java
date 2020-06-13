package com.retheviper.springbootsample.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

/**
 * JPA auditable class.
 *
 * @author retheviper
 * @see AuditingEntityListener
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

    /**
     * Created by
     */
    @CreatedBy
    private U createdBy;

    /**
     * Created date
     */
    @CreatedDate
    private LocalDateTime createdDate;

    /**
     * Last modified by
     */
    @LastModifiedBy
    private U lastModifiedBy;

    /**
     * Last modified date
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
