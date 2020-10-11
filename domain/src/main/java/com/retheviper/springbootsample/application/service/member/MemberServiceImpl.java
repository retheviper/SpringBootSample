package com.retheviper.springbootsample.application.service.member;

import com.retheviper.springbootsample.application.dto.member.MemberDto;
import com.retheviper.springbootsample.common.constant.MemberRole;
import com.retheviper.springbootsample.common.constant.message.MemberExceptionMessage;
import com.retheviper.springbootsample.common.exception.MemberException;
import com.retheviper.springbootsample.domain.entity.member.Member;
import com.retheviper.springbootsample.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
     * Member repository
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
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public MemberDto getMember(final MemberDto dto) {
        return createDto(getEntity(dto.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public MemberDto createMember(final MemberDto dto) {
        if (this.repository.existsByUserId(dto.getUserId())) {
            throw new MemberException(MemberExceptionMessage.E002.getValue());
        }
        final Member created = this.mapper.map(dto, Member.class);
        created.setPassword(this.encoder.encode(dto.getPassword()));
        created.setAccountNonExpired(true);
        created.setAccountNonLocked(true);
        created.setCredentialsNonExpired(true);
        created.setEnabled(true);
        created.setRoles(Collections.singletonList(MemberRole.USER));
        return save(created);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public MemberDto updateMember(final MemberDto dto) {
        final Member entity = getEntity(dto.getId(), dto.getPassword());
        entity.setName(dto.getName());
        if (dto.getNewPassword() != null) {
            entity.setPassword(this.encoder.encode(dto.getNewPassword()));
        }
        return save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteMember(final MemberDto dto) {
        final Member existing = getEntity(dto.getId(), dto.getPassword());
        this.repository.deleteById(existing.getId());
    }

    /**
     * Save entity to repository and get result.
     *
     * @param entity entity to save
     * @return Saved result as DTO
     */
    private MemberDto save(final Member entity) {
        return this.createDto(this.repository.save(entity));
    }

    /**
     * Map entity to DTO.
     *
     * @param entity Member entity
     * @return Member DTO
     */
    private MemberDto createDto(final Member entity) {
        return this.mapper.map(entity, MemberDto.class);
    }

    /**
     * Get entity from repository.
     *
     * @param id Member's ID
     * @return Member entity
     */
    private Member getEntity(final long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new MemberException(MemberExceptionMessage.E003.getValue()));
    }

    /**
     * Get entity from repository and check member's password matches with inputted value.
     *
     * @param id Member's ID
     * @return Entity that password checked
     */
    private Member getEntity(final long id, final String password) {
        final Member entity = getEntity(id);
        if (this.encoder.matches(password, entity.getPassword())) {
            return entity;
        } else {
            throw new MemberException(MemberExceptionMessage.E001.getValue());
        }
    }
}
