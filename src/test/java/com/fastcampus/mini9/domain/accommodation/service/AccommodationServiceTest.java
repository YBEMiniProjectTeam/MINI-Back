package com.fastcampus.mini9.domain.accommodation.service;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;
import com.fastcampus.mini9.domain.accommodation.entity.accommodation.AccommodationDetails;
import com.fastcampus.mini9.domain.accommodation.entity.accommodation.AccommodationImage;
import com.fastcampus.mini9.domain.accommodation.entity.location.Location;
import com.fastcampus.mini9.domain.accommodation.entity.location.Region;
import com.fastcampus.mini9.domain.accommodation.entity.room.Room;
import com.fastcampus.mini9.domain.accommodation.entity.room.RoomDetails;
import com.fastcampus.mini9.domain.accommodation.entity.room.Stock;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.accommodation.repository.RegionRepository;
import com.fastcampus.mini9.domain.accommodation.repository.RoomDetailsRepository;
import com.fastcampus.mini9.domain.accommodation.repository.RoomRepository;
import com.fastcampus.mini9.domain.accommodation.vo.AccommodationType;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccommodationServiceTest {


	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private AccommodationService accommodationService;
	@Autowired
	private AccommodationRepository accommodationRepository;

	@Autowired
	private RoomDetailsRepository roomDetailsRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private EntityManager entityManager;


	private Accommodation accommodation;
	private Region region;
	private Room room;
	private AccommodationDetails details;
	private RoomDetails roomDetails;
	private Stock stock;

	@BeforeEach
	public void setDate(){

		stock = Stock.builder()
			.date(LocalDate.now())
			.quantity(10)
			.build();


		// roomDetails = RoomDetails.builder()
		// 	.id(1L)
		// 	.airConditioner(true)
		// 	.bathFacility(true)
		// 	.bathtub(true)
		// 	.homeTheater(true)
		// 	.airConditioner(true)
		// 	.tv(true)
		// 	.pc(true)
		// 	.internet(true)
		// 	.refrigerator(true)
		// 	.toiletries(true)
		// 	.sofa(true)
		// 	.cookware(true)
		// 	.diningTable(true)
		// 	.hairDryer(true)
		// 	.build();


		room = Room.builder()
			.name("test room")
			.price(400)
			.numberOfRoom(3)
			.capacity(2)
			.capacityMax(4)
			.stocks(List.of(stock))
			.payments(new ArrayList<>())
			.build();


		accommodation = Accommodation.builder()
			.type(AccommodationType.HOTEL)
			.name("test")
			.images(new ArrayList<>())
			.checkIn(LocalTime.now())
			.checkOut(LocalTime.now())
			.details(details)
			.rooms(List.of(room))
			.build();

		details = AccommodationDetails.builder()
			.id(1L)
			.description("description")
			.tel("010-1234-1234")
			.cooking(true)
			.parking(true)
			.others("none")
			.address("서울시 강남구")
			.latitude("1234123412341234")
			.longitude("123412341234")
			.accommodation(accommodation)
			.build();
	}

	@Test
	@DisplayName("/accommodation 응답값")
	public void accommodationService() throws Exception {

		accommodationRepository.save(accommodation);

		mockMvc.perform(get("/accommodations"))
			.andExpect(status().isOk())
			.andDo(print());
	}


	@Test
	@DisplayName("/accommodations/{accommodationId} 응답값")
	public void findAccommodationService() throws Exception {

		Accommodation savedAccommodation = accommodationRepository.save(accommodation);

		mockMvc.perform(get("/accommodations/"+savedAccommodation.getId()))
			.andExpect(status().isOk())
			.andDo(print());
	}


	@Test
	@DisplayName("/accommodations/{accommodationId}/rooms 응답값")
	public void findAccommodationRoomService() throws Exception {

		// Room room1 = roomRepository.save(room);

		Accommodation savedAccommodation = accommodationRepository.save(accommodation);

		mockMvc.perform(get("/accommodations/"+savedAccommodation.getId()+"/rooms")
				.param("start_date","2023-12-13")
				.param("end_date","2023-12-13")
				.param("guest_num", "2"))
			.andExpect(status().isOk())
			.andDo(print());
	}

}