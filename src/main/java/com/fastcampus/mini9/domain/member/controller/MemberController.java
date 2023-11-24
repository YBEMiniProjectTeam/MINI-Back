package com.fastcampus.mini9.domain.member.controller;

import com.fastcampus.mini9.common.response.DataResponseBody;
import com.fastcampus.mini9.domain.member.controller.dto.MemberDtoMapper;
import com.fastcampus.mini9.domain.member.controller.dto.request.SignupRequestDto;
import com.fastcampus.mini9.domain.member.controller.dto.response.MemberSaveResponseDto;
import com.fastcampus.mini9.domain.member.service.MemberService;
import com.fastcampus.mini9.domain.member.service.dto.response.MemberDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberDtoMapper mapper;

    @PostMapping("/sign-up")
    public DataResponseBody<MemberSaveResponseDto> signUp(
        @RequestBody @Valid SignupRequestDto request
    ) {
        MemberDto response = memberService.save(mapper.signupTomMemberSave(request));
        return DataResponseBody.success(mapper.memberToMemberSaveResponse(response));
    }
}
