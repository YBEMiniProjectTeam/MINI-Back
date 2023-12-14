package com.fastcampus.mini9.domain.accommodation.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;
import com.fastcampus.mini9.domain.accommodation.entity.accommodation.AccommodationDetails;
import com.fastcampus.mini9.domain.accommodation.entity.location.Region;
import com.fastcampus.mini9.domain.accommodation.entity.room.Room;
import com.fastcampus.mini9.domain.accommodation.entity.room.RoomDetails;
import com.fastcampus.mini9.domain.accommodation.entity.room.Stock;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.accommodation.repository.RegionRepository;
import com.fastcampus.mini9.domain.accommodation.repository.RoomRepository;
import com.fastcampus.mini9.domain.accommodation.repository.StockRepository;
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
	private RoomRepository roomRepository;
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private StockRepository stockRepository;
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


		room = Room.builder()
			.name("test room")
			.price(400)
			.numberOfRoom(3)
			.capacity(2)
			.capacityMax(4)
			.stocks(List.of(stock))
			.payments(new ArrayList<>())
			.build();

		roomRepository.save(room);


		roomDetails = RoomDetails.builder()
			.id(room.getId())
			.airConditioner(true)
			.bathFacility(true)
			.bathtub(true)
			.homeTheater(true)
			.airConditioner(true)
			.tv(true)
			.pc(true)
			.internet(true)
			.refrigerator(true)
			.toiletries(true)
			.sofa(true)
			.cookware(true)
			.diningTable(true)
			.hairDryer(true)
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
	@DisplayName("/accommodations/{accommodationId}/rooms 응답값 성공경우")
	public void accommodationWithDetailsSuccess() throws Exception {

		Stock stocks1 = Stock.builder()
			.date(LocalDate.of(2023,12,11))
			.quantity(4)
			.build();

		Stock stocks2 = Stock.builder()
			.date(LocalDate.of(2023,12,12))
			.quantity(4)
			.build();


		Stock stocks3 = Stock.builder()
			.date(LocalDate.of(2023,12,13))
			.quantity(4)
			.build();

		stockRepository.saveAll(List.of(stocks1,stocks2,stocks3));


		entityManager.merge(roomDetails);
		room = Room.builder()
			.name("test room")
			.price(400)
			.numberOfRoom(3)
			.capacity(2)
			.capacityMax(4)
			.payments(new ArrayList<>())
			.details(roomDetails)
			.stocks(List.of(stocks1,stocks2,stocks3))
			.build();

		Room savedRoom = roomRepository.save(room);


		roomDetails = RoomDetails.builder()
			.id(savedRoom.getId())
			.airConditioner(true)
			.bathFacility(true)
			.bathtub(true)
			.homeTheater(true)
			.airConditioner(true)
			.tv(true)
			.pc(true)
			.internet(true)
			.refrigerator(true)
			.toiletries(true)
			.sofa(true)
			.cookware(true)
			.diningTable(true)
			.hairDryer(true)
			.build();

		details = AccommodationDetails.builder()
			.id(1L)
			.description("test")
			.address("서울시 강남구")
			.latitude("123123123123")
			.longitude("123123123123")
			.tel("01023124123")
			.parking(true)
			.cooking(true)
			.others("others")
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

		Accommodation savedAccommodation = accommodationRepository.save(accommodation);

		mockMvc.perform(get("/accommodations/"+savedAccommodation.getId()+"/rooms")
				.param("start_date","2023-12-11")
				.param("end_date","2023-12-13")
				.param("guest_num", "2"))
			.andExpect(status().isOk())
			.andDo(print());


	}

	@Test
	@DisplayName("/accommodations/{accommodationId}/rooms 응답값 실패일경우")
	public void accommodationWithDetails() throws Exception {

		Stock stocks1 = Stock.builder()
			.date(LocalDate.of(2023,12,11))
			.quantity(4)
			.build();

		Stock stocks2 = Stock.builder()
			.date(LocalDate.of(2023,12,12))
			.quantity(0)
			.build();


		Stock stocks3 = Stock.builder()
			.date(LocalDate.of(2023,12,13))
			.quantity(4)
			.build();

		stockRepository.saveAll(List.of(stocks1,stocks2,stocks3));

		room = Room.builder()
			.name("test room")
			.price(400)
			.numberOfRoom(3)
			.capacity(2)
			.capacityMax(4)
			.payments(new ArrayList<>())
			.details(roomDetails)
			.stocks(List.of(stocks1,stocks2,stocks3))
			.build();

		Room savedRoom = roomRepository.save(room);


		roomDetails = RoomDetails.builder()
			.id(savedRoom.getId())
			.airConditioner(true)
			.bathFacility(true)
			.bathtub(true)
			.homeTheater(true)
			.airConditioner(true)
			.tv(true)
			.pc(true)
			.internet(true)
			.refrigerator(true)
			.toiletries(true)
			.sofa(true)
			.cookware(true)
			.diningTable(true)
			.hairDryer(true)
			.build();

		details = AccommodationDetails.builder()
			.id(1L)
			.description("test")
			.address("서울시 강남구")
			.latitude("123123123123")
			.longitude("123123123123")
			.tel("01023124123")
			.parking(true)
			.cooking(true)
			.others("others")
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

		Accommodation savedAccommodation = accommodationRepository.save(accommodation);

		mockMvc.perform(get("/accommodations/"+savedAccommodation.getId()+"/rooms")
				.param("start_date","2023-12-11")
				.param("end_date","2023-12-13")
				.param("guest_num", "2"))
			.andExpect(status().isOk())
			.andDo(print());


	}

}