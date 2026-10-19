package com.project.sneakermarket.member.service;

import com.project.sneakermarket.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public boolean isAdmin(Long memberId) {
        return memberRepository.findByMemberIdAndDeletedAtIsNull(memberId)
                .map(member -> "ROLE_ADMIN".equals(member.getRole()))
                .orElse(false);
    }
}
