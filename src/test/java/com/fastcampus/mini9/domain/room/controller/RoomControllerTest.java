package com.fastcampus.mini9.domain.room.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.mini9.domain.room.controller.dto.RoomDtoMapper;
import com.fastcampus.mini9.domain.room.service.RoomService;

@WebMvcTest(RoomController.class)
class RoomControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RoomService roomService;

	@Test
	@WithMockUser
	@DisplayName("숙소 기준 객실 정보 파라미터 테스트")
	public void roomsInfoTest() throws Exception {

		//given

		given(roomService.roomsInfo(any(), any(), any(), any()))
			.willReturn(ResponseEntity.ok(
				RoomDtoMapper.INSTANCE.roomResDto(
					1L,
					"2023-11-20",
					"2023-11-22",
					"2")
			));

		mockMvc.perform(get("/accommodations/1/rooms?start_date=2023-11-20&end_date=2023-11-22&guest_num=2"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.accommodationId").value(1L))
			.andExpect(jsonPath("$.startDate").value("2023-11-20"))
			.andExpect(jsonPath("$.endDate").value("2023-11-22"))
			.andExpect(jsonPath("$.guestNum").value("2"))
			.andDo(print());
	}

	@Test
	@DisplayName("객실 상세정보 테스트")
	public void detailRoomTest() {

	}

}