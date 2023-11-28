package com.fastcampus.mini9.domain.member.service;

import com.fastcampus.mini9.domain.member.entity.Member;
import com.fastcampus.mini9.domain.member.exception.ExistsArgumentException;
import com.fastcampus.mini9.domain.member.exception.NotFoundMemberException;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;
import com.fastcampus.mini9.domain.member.service.dto.request.MemberSaveDto;
import com.fastcampus.mini9.domain.member.service.dto.response.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDto save(MemberSaveDto dto) {
        if (memberRepository.findByEmail(dto.email()).isPresent()) {
            throw new ExistsArgumentException("이미 존재하는 이메일입니다.");
        }
        String encodePassword = passwordEncoder.encode(dto.pwd());
        Member member = dto.toEntity(encodePassword);
        Member saveMember = memberRepository.save(member);
        return MemberDto.toDto(saveMember);
    }

    public MemberDto getProfile(String email) {
        Member member = findByEmail(email);
        return MemberDto.toDto(member);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(NotFoundMemberException::new);
    }
}
