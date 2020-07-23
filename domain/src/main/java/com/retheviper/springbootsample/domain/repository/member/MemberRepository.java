package com.retheviper.springbootsample.domain.repository.member;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.retheviper.springbootsample.domain.entity.member.Member;

/**
 * Member repository.
 *
 * @author retheviper
 */
public interface MemberRepository extends CrudRepository<Member, Long> {

    /**
     * Check member exists by user ID.
     *
     * @param userId user ID
     * @return result of check
     */
    boolean existsByUserId(String userId);

    /**
     * Search member by user ID.
     *
     * @param userId user ID
     * @return result of search
     */
    Optional<Member> findByUserId(String userId);
}
