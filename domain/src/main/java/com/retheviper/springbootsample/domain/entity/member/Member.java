package com.retheviper.springbootsample.domain.entity.member;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.retheviper.springbootsample.domain.entity.common.Auditable;

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
    private String userId;

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
     * Member that this information belongs
     */
    @OneToOne
    @JoinColumn(name = "member_information_id")
    private MemberInformation memberInformation;

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
    private List<String> roles;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return this.userId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles != null
                ? this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableList())
                : Collections.emptyList();
    }
}