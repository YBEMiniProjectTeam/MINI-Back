package com.fastcampus.mini9.domain.room.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.mini9.domain.room.controller.dto.RoomSpecificDto;
import com.fastcampus.mini9.domain.room.entity.Room;
import com.fastcampus.mini9.domain.room.entity.RoomDetailInfo;
import com.fastcampus.mini9.domain.room.entity.RoomImage;
import com.fastcampus.mini9.domain.room.entity.Stock;
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

		// given(roomService.roomsInfo(any(), any(), any(), any()))
		// 	.willReturn(ResponseEntity.ok(
		// 		new TestDto.roomPramDto(
		// 			1L,
		// 			"2023-11-20",
		// 			"2023-11-22",
		// 			"2")
		// 	));
		//
		// mockMvc.perform(get("/accommodations/1/rooms?start_date=2023-11-20&end_date=2023-11-22&guest_num=2"))
		// 	.andExpect(status().isOk())
		// 	.andExpect(jsonPath("$.accommodationId").value(1L))
		// 	.andExpect(jsonPath("$.startDate").value("2023-11-20"))
		// 	.andExpect(jsonPath("$.endDate").value("2023-11-22"))
		// 	.andExpect(jsonPath("$.guestNum").value("2"))
		// 	.andDo(print());

		//then
		mockMvc.perform(get("/accommodations/1/rooms")
				.param("start_date", "2023-11-20")
				.param("end_date", "2023-11-22")
				.param("guest_num", "2")
			).andExpect(status().isOk())
			.andDo(print());

		//then

	}

	@Test
	@WithMockUser
	@DisplayName("객실 상세정보 테스트")
	public void detailRoomTest() throws Exception {

		//given
		Stock stock1 = Stock.builder()
			.date(LocalDate.ofEpochDay(2023 - 12 - 25))
			.quantity(3)
			.build();

		Stock stock2 = Stock.builder()
			.date(LocalDate.ofEpochDay(2023 - 12 - 25))
			.quantity(3)
			.build();

		RoomImage img1 = RoomImage.builder()
			.url("https://naver.com")
			.build();

		RoomImage img2 = RoomImage.builder()
			.url("https://google.com")
			.build();

		RoomDetailInfo roomDetailInfo = RoomDetailInfo.builder()
			.tv(true)
			.table(true)
			.refrigerator(true)
			.airConditioner(true)
			.bathTub(true)
			.internet(true)
			.build();

		Room room = Room.builder()
			.name("민들레실")
			.price(10000)
			.stockList(List.of(stock1, stock2))
			.capacity(2)
			.capacityMax(4)
			.description("1번 객실입니다. 좋아요")
			.roomImageList(List.of(img1, img2))
			.roomDetailInfo(roomDetailInfo)
			.build();

		given(roomService.detailRoom(any()))
			.willReturn(ResponseEntity.ok(RoomSpecificDto.fromEntity(room)));

		mockMvc.perform(get("/rooms/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("민들레실"))
			.andExpect(jsonPath("$.price").value(10000))
			.andExpect(jsonPath("$.capacity").value(2))
			.andExpect(jsonPath("$.capacity_max").value(4))
			.andExpect(jsonPath("$.intro_message").value("1번 객실입니다. 좋아요"))
			//현재 민들레실에 있는 전체 객실수
			.andExpect(jsonPath("$.stock_quantity").value(2))

			.andExpect(jsonPath("$.room_detail_info.bathTub").value(true))
			.andExpect(jsonPath("$.room_detail_info.airConditioner").value(true))
			.andExpect(jsonPath("$.room_detail_info.tv").value(true))
			.andExpect(jsonPath("$.room_detail_info.internet").value(true))
			.andExpect(jsonPath("$.room_detail_info.refrigerator").value(true))
			.andExpect(jsonPath("$.room_detail_info.table").value(true))

			.andExpect(jsonPath("$.room_image[0].url").value("https://naver.com"))
			.andExpect(jsonPath("$.room_image[1].url").value("https://google.com"))
			.andDo(print());

	}

}