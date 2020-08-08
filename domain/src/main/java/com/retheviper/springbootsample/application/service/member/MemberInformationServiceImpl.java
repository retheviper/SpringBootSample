package com.retheviper.springbootsample.application.service.member;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retheviper.springbootsample.application.dto.member.MemberInformationDto;
import com.retheviper.springbootsample.common.constant.message.MemberExceptionMessage;
import com.retheviper.springbootsample.common.exception.MemberException;
import com.retheviper.springbootsample.domain.entity.member.MemberInformation;
import com.retheviper.springbootsample.domain.repository.member.MemberInformationRepository;

import lombok.RequiredArgsConstructor;

/**
 * Member information service class. (Implementation)
 *
 * @author retheviper
 */
@Service
@RequiredArgsConstructor
public class MemberInformationServiceImpl implements MemberInformationService {

    /**
     * Data model converter
     */
    private final ModelMapper mapper;

    /**
     * Member information repository
     */
    private final MemberInformationRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<MemberInformationDto> listMemberInformation() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(this::createDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public MemberInformationDto getMemberInformation(final MemberInformationDto dto) {
        return createDto(getEntity(dto.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public MemberInformationDto createMemberInformation(final MemberInformationDto dto) {
        return save(this.mapper.map(dto, MemberInformation.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public MemberInformationDto updateMemberInformation(final MemberInformationDto dto) {
        if (!this.repository.existsById(dto.getId())) {
            throw new MemberException(MemberExceptionMessage.E000.getValue());
        }
        final MemberInformation destination = getEntity(dto.getId());
        final MemberInformation source = this.mapper.map(dto, MemberInformation.class);
        this.mapper.map(source, destination);
        return save(destination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteMemberInformation(final MemberInformationDto dto) {
        this.repository.deleteById(dto.getId());
    }


    /**
     * Save entity to repository and get result.
     *
     * @param entity enitity to save
     * @return Saved result as DTO
     */
    private MemberInformationDto save(final MemberInformation entity) {
        return this.createDto(this.repository.save(entity));
    }

    /**
     * Map entity to DTO.
     *
     * @param entity Member entity
     * @return Member DTO
     */
    private MemberInformationDto createDto(final MemberInformation entity) {
        return this.mapper.map(entity, MemberInformationDto.class);
    }

    /**
     * Get entity from repository.
     *
     * @param id Member's ID
     * @return Member entity
     */
    private MemberInformation getEntity(final long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new MemberException(MemberExceptionMessage.E003.getValue()));
    }
}
