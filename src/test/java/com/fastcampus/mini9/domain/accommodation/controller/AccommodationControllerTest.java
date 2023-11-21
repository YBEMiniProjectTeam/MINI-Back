package com.fastcampus.mini9.domain.accommodation.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.mini9.domain.accommodation.service.AccommodationService;

@WebMvcTest(AccommodationController.class)
class AccommodationControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	AccommodationService accommodationService;

	@Test
	@DisplayName("숙소 상세정보 조회")
	public void displayAccommodationInfo() {

	}

}