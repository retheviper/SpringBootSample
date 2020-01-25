package com.retheviper.springbootsample.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retheviper.springbootsample.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Boolean existsByMemberId(String memberId);

	Optional<Member> findByMemberId(String memberId);

	void deleteByMemberId(String memberID);
}
