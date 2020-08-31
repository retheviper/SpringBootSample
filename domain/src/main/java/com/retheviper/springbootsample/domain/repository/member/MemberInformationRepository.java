package com.retheviper.springbootsample.domain.repository.member;

import com.retheviper.springbootsample.domain.entity.member.MemberInformation;
import org.springframework.data.repository.CrudRepository;

/**
 * Member information repository.
 *
 * @author retheviper
 */
public interface MemberInformationRepository extends CrudRepository<MemberInformation, Long> {
}
