package com.fastcampus.mini9.domain.payment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.mini9.domain.member.repository.MemberRepository;
import com.fastcampus.mini9.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PaymentServiceTest {


	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRepository memberRepository;

	private ObjectMapper objectMapper = new ObjectMapper();

}