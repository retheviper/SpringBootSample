package com.retheviper.springbootsample.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.retheviper.springbootsample.domain.entity.Member;

/**
 * Member repository.
 *
 * @author retheviper
 */
public interface MemberRepository extends CrudRepository<Member, Long> {

    /**
     * Check member exists.
     *
     * @param uid member's ID(user ID)
     * @return result of check
     */
    boolean existsByUid(String uid);

    /**
     * Find member by ID.
     *
     * @param uid member's ID(user ID)
     * @return result of query
     */
    Optional<Member> findByUid(String uid);

    /**
     * Delete member by ID.
     *
     * @param uid member's ID(user ID)
     */
    void deleteByUid(String uid);
}
