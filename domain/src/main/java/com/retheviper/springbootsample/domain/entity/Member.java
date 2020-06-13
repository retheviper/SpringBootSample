package com.retheviper.springbootsample.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Member entity class.
 *
 * @author retheviper
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Member extends Auditable<String> implements UserDetails {

    /**
     * Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Member's ID(user ID)
     */
    @Column(nullable = false, unique = true, length = 16)
    private String uid;

    /**
     * Member's name
     */
    @Column(nullable = false, length = 16)
    private String name;

    /**
     * Member's password
     */
    @Column(nullable = false)
    private String password;

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
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return this.uid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableList());
    }
}