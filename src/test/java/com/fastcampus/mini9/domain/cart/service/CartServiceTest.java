package com.fastcampus.mini9.domain.cart.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
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
import com.fastcampus.mini9.domain.accommodation.service.AccommodationService;
import com.fastcampus.mini9.domain.accommodation.vo.AccommodationType;
import com.fastcampus.mini9.domain.cart.dto.CreateCartRequest;
import com.fastcampus.mini9.domain.cart.dto.FindCartResponse;
import com.fastcampus.mini9.domain.cart.entity.Cart;
import com.fastcampus.mini9.domain.cart.repository.CartRepository;
import com.fastcampus.mini9.domain.member.entity.Member;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

class CartServiceTest {



	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private AccommodationService accommodationService;
	@Autowired
	private AccommodationRepository accommodationRepository;

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private CartService cartService;


	private Accommodation accommodation;
	private Region region;
	private Room room;
	private AccommodationDetails details;
	private RoomDetails roomDetails;
	private Stock stock;


	@Test
	@DisplayName("/carts 추가")
	@WithMockUser(roles = "USER")
	public void addCarts() throws Exception {
		room = Room.builder()
			.name("test room")
			.price(400)
			.numberOfRoom(3)
			.capacity(2)
			.capacityMax(4)
			.payments(new ArrayList<>())
			.build();

		Member member = Member.builder()
			.birthday(LocalDate.now())
			.email("test@gmail.com")
			.pwd("test123123")
			.name("testMember")
			.build();


		Room savedRoom = roomRepository.save(room);
		Member savedMember = memberRepository.save(member);

		Cart cart = Cart.builder()
			.checkInDate(LocalDate.now())
			.checkOutDate(LocalDate.now())
			.room(savedRoom)
			.member(savedMember)
			.build();

		Cart savedCart = cartRepository.save(cart);
		CreateCartRequest createCartRequest = new CreateCartRequest(
			savedRoom.getId(),
			LocalDate.now(),
			LocalDate.now()
		);

		Long cartId = cartService.addCart(createCartRequest, member.getId());

		assertEquals(cartId, savedCart.getId() );
	}


	@Test
	@DisplayName("/carts 찾기")
	@WithMockUser(roles = "USER")
	public void findAllCarts() throws Exception {


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
			.build();


		Accommodation savedAccommodation = accommodationRepository.save(accommodation);

		room = Room.builder()
			.name("test room")
			.price(400)
			.numberOfRoom(3)
			.capacity(2)
			.capacityMax(4)
			.payments(new ArrayList<>())
			.details(roomDetails)
			.stocks(new ArrayList<>())
			.accommodation(accommodation)
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


		Member member = Member.builder()
			.birthday(LocalDate.now())
			.email("test@gmail.com")
			.pwd("test123123")
			.name("testMember")
			.build();


		Member savedMember = memberRepository.save(member);

		Cart cart = Cart.builder()
			.checkInDate(LocalDate.now())
			.checkOutDate(LocalDate.now())
			.room(savedRoom)
			.member(savedMember)
			.build();

		Cart savedCart = cartRepository.save(cart);
		CreateCartRequest createCartRequest = new CreateCartRequest(
			savedRoom.getId(),
			LocalDate.now(),
			LocalDate.now()
		);


		List<FindCartResponse> findCartResponses= cartService.findCarts(member.getId());

		assertEquals(findCartResponses.size(), 1);
	}





}