package com.retheviper.springbootsample.application.service.member;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.retheviper.springbootsample.common.constant.message.MemberExceptionMessage;
import com.retheviper.springbootsample.common.exception.MemberException;
import com.retheviper.springbootsample.domain.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;

/**
 * User details service class. (Implementation)
 *
 * @author retheviper
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Member repository
     */
    private final MemberRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return this.repository.findByUserId(username)
                .orElseThrow(() -> new MemberException(MemberExceptionMessage.E003.getValue()));
    }
}
