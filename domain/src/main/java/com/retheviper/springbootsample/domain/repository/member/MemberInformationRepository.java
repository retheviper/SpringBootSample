package com.retheviper.springbootsample.domain.repository.member;

import org.springframework.data.repository.CrudRepository;

import com.retheviper.springbootsample.domain.entity.member.MemberInformation;

/**
 * Member information repository.
 *
 * @author retheviper
 */
public interface MemberInformationRepository extends CrudRepository<MemberInformation, Long> {
}
