package com.retheviper.springbootsample.application.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retheviper.springbootsample.application.dto.MemberDto;
import com.retheviper.springbootsample.common.constant.MemberRole;
import com.retheviper.springbootsample.common.constant.message.MemberExceptionMessage;
import com.retheviper.springbootsample.common.exception.MemberException;
import com.retheviper.springbootsample.domain.entity.Member;
import com.retheviper.springbootsample.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

/**
 * Member service class. (Implementation)
 *
 * @author retheviper
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Password encryptor
     */
    private final PasswordEncoder encoder;

    /**
     * Member repository class
     */
    private final MemberRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<MemberDto> listMember() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(this::createDto)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public MemberDto getMember(final String uid) {
        return createDto(getEntity(uid));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public MemberDto getMember(final MemberDto dto) {
        return createDto(getEntity(dto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public MemberDto createMember(final MemberDto dto) {
        if (this.repository.existsByUid(dto.getUid())) {
            throw new MemberException(MemberExceptionMessage.E002.getValue());
        }
        final MemberDto created = dto.toBuilder()
                .password(this.encoder.encode(dto.getPassword()))
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .roles(Collections.singletonList(MemberRole.USER.getRole()))
                .build();
        return createDto(this.repository.save(this.mapper.map(created, Member.class)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public MemberDto updateMember(final MemberDto dto) {
        final Member entity = getEntity(dto);
        entity.setPassword(dto.getNewPassword() != null
                ? this.encoder.encode(dto.getNewPassword())
                : this.encoder.encode(dto.getPassword()));
        return createDto(this.repository.save(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteMember(final MemberDto dto) {
        this.repository.deleteByUid(getEntity(dto).getUid());
    }

    /**
     * Map entity to DTO.
     *
     * @param entity
     * @return
     */
    private MemberDto createDto(final Member entity) {
        return this.mapper.map(entity, MemberDto.class);
    }

    /**
     * Get entity from repository.
     *
     * @param uid
     * @return
     */
    private Member getEntity(final String uid) {
        return this.repository.findByUid(uid)
                .orElseThrow(() -> new MemberException(MemberExceptionMessage.E003.getValue()));
    }

    /**
     * Get entity from repository and check member's password matches with inputed value.
     *
     * @param dto
     * @return Entity that password checked
     */
    private Member getEntity(final MemberDto dto) {
        final Member entity = getEntity(dto.getUid());
        if (this.encoder.matches(dto.getPassword(), entity.getPassword())) {
            return entity;
        } else {
            throw new MemberException(MemberExceptionMessage.E001.getValue());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return getEntity(username);
    }
}
