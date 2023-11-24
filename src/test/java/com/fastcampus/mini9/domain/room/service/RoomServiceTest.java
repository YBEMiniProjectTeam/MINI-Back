package com.fastcampus.mini9.domain.room.service;

import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;
import com.fastcampus.mini9.domain.accommodation.entity.AccommodationType;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.room.controller.dto.RoomResDtoList;
import com.fastcampus.mini9.domain.room.entity.Room;
import com.fastcampus.mini9.domain.room.entity.RoomImage;
import com.fastcampus.mini9.domain.room.entity.Stock;
import com.fastcampus.mini9.domain.room.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

	@InjectMocks
	RoomService roomService;
	@Mock
	RoomRepository roomRepository;

	@Mock
	AccommodationRepository accommodationRepository;

	@Test
	@DisplayName("객실 상세 정보 검색 결과 성공 케이스")
	public void searchingRoomInfo() {
		//given

		Stock stock11 = Stock.builder()
			.date(LocalDate.of(2023, 12, 1))
			.quantity(3)
			.build();

		Stock stock12 = Stock.builder()
			.date(LocalDate.of(2023, 12, 2))
			.quantity(3)
			.build();

		Stock stock13 = Stock.builder()
			.date(LocalDate.of(2023, 12, 3))
			.quantity(3)
			.build();

		RoomImage img11 = RoomImage.builder()
			.url("https://naver.com")
			.build();

		RoomImage img12 = RoomImage.builder()
			.url("https://google.com")
			.build();

		Room room1 = Room.builder()
			.name("백두산실")
			.price(10000)
			.checkIn(LocalDate.of(2023, 12, 3))
			.checkOut(LocalDate.of(2023, 12, 3))
			.stockList(List.of(stock11, stock12, stock13))
			.capacity(2)
			.capacityMax(4)
			.description("1번 객실입니다. 좋아요11")
			.roomImageList(List.of(img11, img12))
			.build();

		Stock stock21 = Stock.builder()
			.date(LocalDate.of(2023, 12, 4))
			.quantity(3)
			.build();

		Stock stock22 = Stock.builder()
			.date(LocalDate.of(2023, 12, 6))
			.quantity(3)
			.build();

		RoomImage img21 = RoomImage.builder()
			.url("https://naver.com")
			.build();

		RoomImage img22 = RoomImage.builder()
			.url("https://google.com")
			.build();

		Room room2 = Room.builder()
			.name("한라산실")
			.price(10000)
			.checkIn(LocalDate.of(2023, 12, 3))
			.checkOut(LocalDate.of(2023, 12, 3))
			.stockList(List.of(stock21, stock22))
			.capacity(2)
			.capacityMax(4)
			.description("2번 객실입니다. 좋아요22")
			.roomImageList(List.of(img21, img22))
			.build();

		Stock stock31 = Stock.builder()
			.date(LocalDate.of(2023, 12, 7))
			.quantity(3)
			.build();

		Stock stock32 = Stock.builder()
			.date(LocalDate.of(2023, 12, 9))
			.quantity(3)
			.build();

		RoomImage img31 = RoomImage.builder()
			.url("https://naver.com")
			.build();

		RoomImage img32 = RoomImage.builder()
			.url("https://google.com")
			.build();

		Room room3 = Room.builder()
			.name("금강산실")
			.price(10000)
			.checkIn(LocalDate.of(2023, 12, 3))
			.checkOut(LocalDate.of(2023, 12, 3))
			.stockList(List.of(stock31, stock32))
			.capacity(2)
			.capacityMax(4)
			.description("3번 객실입니다. 좋아요33")
			.roomImageList(List.of(img31, img32))
			.build();

		Accommodation accommodation = Accommodation.builder()
			.rooms(List.of(room1, room2, room3))
			.type(AccommodationType.HOTEL)
			.name("우리나라 좋은나라 여러산")
			.location(null)
			.accommodationImage(new ArrayList<>())
			.build();

		lenient().when(accommodationRepository.findById(any()))
			.thenReturn(Optional.of(accommodation));

		//then

		ResponseEntity<List<RoomResDtoList.RoomResDto>> result = roomService.roomsInfo(1L, LocalDate.of(2023, 12, 4),
			LocalDate.of(2023, 12, 9), 2);

		for (RoomResDtoList.RoomResDto roomResDto : Objects.requireNonNull(result.getBody())) {

			System.out.println(roomResDto.getName());
		}

	}

}

// {
//  "id": 1,
//  "name": "민들레실",
//  "price": 100000,
//  "capacity": 2,
//  "capacity_max": 4,
//  "stock_quantity": 2,
//  "room_image": [
//  {
//  "url": "https://bit.ly/2Z4KKcF"
//  },
//  {
//  "url": "https://bit.ly/2Z4KKcF"
//  }
//  ]
//  },