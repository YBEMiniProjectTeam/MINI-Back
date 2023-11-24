package com.fastcampus.mini9.domain.accommodation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.mini9.domain.accommodation.service.AccommodationService;

@WebMvcTest(AccommodationController.class)
class AccommodationControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	AccommodationService accommodationService;

	@Test
	@WithMockUser
	@DisplayName("숙소 상세정보 조회 성공 케이스")
	public void displayAccommodationInfoSuccess() throws Exception {

		mockMvc.perform(get("/accommodations/1"))
			.andExpect(status().isOk())
			.andDo(print());

	}

	@Test
	@WithMockUser
	@DisplayName("숙소 상세정보 조회 실패 케이스")
	public void displayAccommodationInfoFail() throws Exception {

		// mockMvc.perform(get("/accommodations/3"))
		// 	.andExpect(status().is5xxServerError());

	}

}