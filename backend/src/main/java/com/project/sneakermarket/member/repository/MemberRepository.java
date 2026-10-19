package com.project.sneakermarket.member.repository;

import com.project.sneakermarket.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberIdAndDeletedAtIsNull(Long memberId);
}
