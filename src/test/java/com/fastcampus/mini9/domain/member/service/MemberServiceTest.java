package com.fastcampus.mini9.domain.member.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.mini9.domain.member.controller.dto.request.LoginRequestDto;
import com.fastcampus.mini9.domain.member.controller.dto.request.SignupRequestDto;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberServiceTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRepository memberRepository;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	@DisplayName("회원가입")
	public void memberRegister() throws Exception {

		SignupRequestDto signupRequestDto = new SignupRequestDto(
			"test@gmail.com",
			"test123123123",
			"test owner",
			LocalDate.of(2021, 10, 1)
		);

		String content = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.writeValueAsString(signupRequestDto);

		mockMvc.perform(post("/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
			)
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@DisplayName("로그인 성공")
	public void memberLogin() throws Exception {

		memberRegister();

		LoginRequestDto loginRequestDto = new LoginRequestDto(
			"test@gmail.com",
			"test123123123"
		);
		String content2 = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.writeValueAsString(loginRequestDto);

		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content2)
			).andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@DisplayName("로그인 실패")
	public void memberLoginFail() throws Exception {

		memberRegister();

		LoginRequestDto loginRequestDto = new LoginRequestDto(
			"test@gmail.com",
			"test"
		);
		String content2 = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.writeValueAsString(loginRequestDto);

		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content2)
			).andExpect(status().isOk())
			.andDo(print());
		;

	}

}