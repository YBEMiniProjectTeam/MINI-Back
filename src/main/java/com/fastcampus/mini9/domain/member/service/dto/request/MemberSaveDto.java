package com.fastcampus.mini9.domain.member.service.dto.request;

import java.time.LocalDate;

import com.fastcampus.mini9.domain.member.entity.Member;

public record MemberSaveDto(
	String email,
	String pwd,
	String name,
	LocalDate birthday
) {

	public Member toEntity(String encodePassword) {
		return Member.builder()
			.email(email)
			.pwd(encodePassword)
			.name(name)
			.birthday(birthday)
			.build();
	}
}
