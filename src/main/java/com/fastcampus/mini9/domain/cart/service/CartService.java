package com.fastcampus.mini9.domain.cart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.mini9.domain.accommodation.entity.room.Room;
import com.fastcampus.mini9.domain.accommodation.repository.RoomRepository;
import com.fastcampus.mini9.domain.cart.dto.CartIdRequest;
import com.fastcampus.mini9.domain.cart.dto.CartIdsRequest;
import com.fastcampus.mini9.domain.cart.dto.CreateCartRequest;
import com.fastcampus.mini9.domain.cart.dto.CreateOrderRequest;
import com.fastcampus.mini9.domain.cart.dto.FindCartResponse;
import com.fastcampus.mini9.domain.cart.entity.Cart;
import com.fastcampus.mini9.domain.cart.repository.CartRepository;
import com.fastcampus.mini9.domain.member.entity.Member;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;
import com.fastcampus.mini9.domain.payment.entity.Payment;
import com.fastcampus.mini9.domain.payment.entity.PaymentStatus;
import com.fastcampus.mini9.domain.reservation.dto.FindReservationResponse;
import com.fastcampus.mini9.domain.reservation.entity.Reservation;
import com.fastcampus.mini9.domain.reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

	private final CartRepository cartRepository;
	private final MemberRepository memberRepository;
	private final RoomRepository roomRepository;
	private final ReservationRepository reservationRepository;

	public List<FindCartResponse> findCarts(Long memberId) {
		Map<Long, List<Cart>> cartsByAccommodationId = cartRepository.findByMemberId(memberId).stream()
			.collect(Collectors.groupingBy(cart -> cart.getRoom().getAccommodation().getId()));

		return cartsByAccommodationId.values().stream()
			.map(this::mapToFindCartResponse)
			.collect(Collectors.toList());
	}

	private FindCartResponse mapToFindCartResponse(List<Cart> carts) {
		return new FindCartResponse(
			carts.get(0).getRoom().getAccommodation().getName(),
			carts.stream()
				.map(cart -> new FindCartResponse.RoomInfo(
					cart.getId(), // 장바구니 ID
					cart.getQuantity(), // 수량
					cart.getRoom().getAccommodation().getDetails().getAddress(), // 숙소 주소
					cart.getRoom().getName(), // 객실명
					cart.getRoom().getAccommodation().getThumbnail(), // 숙소 썸네일 url
					cart.getRoom().getPrice(), // 객실 가격
					cart.getRoom().getCheckIn(), // 체크인 시간
					cart.getRoom().getCheckOut(), // 체크아웃 시간
					cart.getRoom().getCapacity(), // 기준 인원
					cart.getRoom().getCapacityMax() // 최대 인원
				)).collect(Collectors.toList())
		);
	}

	@Transactional
	public void addCart(CreateCartRequest dto, Long id) {
		Member member = memberRepository.findById(id).orElseThrow();
		Room room = roomRepository.findById(dto.roomId()).orElseThrow();

		// TODO: 체크인, 체크아웃 날짜 검증

		cartRepository.findByCheckInDateAndCheckOutDateAndMemberIdAndRoomId(
				dto.checkInDate(), dto.checkOutDate(),
				member.getId(), room.getId())
			.ifPresentOrElse(
				Cart::increaseQuantity,
				() -> cartRepository.save(Cart.builder()
					.checkInDate(dto.checkInDate())
					.checkOutDate(dto.checkOutDate())
					.member(member)
					.room(room)
					.build())
			);
	}

	@Transactional
	public void removeCart(CartIdsRequest dto) {
		dto.cartIds().forEach(cartId -> {
			Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new NoSuchElementException("장바구니에 해당하는 객실이 존재하지 않습니다."));

			cartRepository.delete(cart);
		});
	}

	public List<FindCartResponse> findOrders(CartIdsRequest dto) {
		Map<Long, List<Cart>> cartsByAccommodationId = dto.cartIds().stream()
			.map(cartId -> cartRepository.findById(cartId)
				.orElseThrow(() -> new NoSuchElementException("장바구니에 해당하는 객실이 존재하지 않습니다.")))
			.collect(Collectors.groupingBy(cart -> cart.getRoom().getAccommodation().getId()));

		return cartsByAccommodationId.values().stream()
			.map(this::mapToFindCartResponse)
			.collect(Collectors.toList());
	}

	@Transactional
	public void increaseCart(CartIdRequest dto) {
		Cart cart = cartRepository.findById(dto.cartId())
			.orElseThrow(() -> new NoSuchElementException("장바구니에 해당하는 객실이 존재하지 않습니다."));

		// TODO: 재고 검증(수량이 재고보다 클 수 없다.)

		cart.increaseQuantity();
	}

	@Transactional
	public void decreaseCart(CartIdRequest dto) {
		Cart cart = cartRepository.findById(dto.cartId())
			.orElseThrow(() -> new NoSuchElementException("장바구니에 해당하는 객실이 존재하지 않습니다."));

		if (cart.getQuantity() <= 1) {
			throw new IllegalArgumentException("수량이 1 보다 작을 수 없습니다.");
		}
		cart.decreaseQuantity();
	}

	@Transactional
	public List<FindReservationResponse> createOrder(CreateOrderRequest dto, Long memberId) {
		List<FindReservationResponse> findReservationResponses = new ArrayList<>();

		// TODO: 숙박일 검증

		Member member = memberRepository.findById(memberId).orElseThrow();
		List<Cart> carts = cartRepository.findAllById(dto.cartIds());

		if (carts.size() != dto.cartIds().size()) {
			throw new IllegalArgumentException();
		}

		for (Cart cart : carts) {

			// TODO: 재고 검증
			// TODO: 재고 감소

			// 결제 생성
			Payment payment = Payment.builder()
				.payAt(LocalDateTime.now())
				.price(cart.getRoom().getPrice())
				.status(PaymentStatus.COMPLETED)
				.member(member)
				.room(cart.getRoom())
				.build();

			// 예약 생성
			Reservation reservation = Reservation.builder()
				.member(member)
				.checkIn(cart.getCheckInDate().atTime(cart.getRoom().getCheckIn()))
				.checkOut(cart.getCheckOutDate().atTime(cart.getRoom().getCheckOut()))
				.name(dto.reservationName())
				.reservationNo("No.123123123(임시)")
				.build();
			reservation.setPayment(payment);

			reservationRepository.save(reservation);

			findReservationResponses.add(new FindReservationResponse(cart.getRoom().getAccommodation().getName(),
				new FindReservationResponse.RoomInfo(cart.getRoom().getName(), payment.getPrice(),
					reservation.getCheckIn(), reservation.getCheckOut(), cart.getRoom().getCapacity(),
					cart.getRoom().getCapacityMax())));
		}
		return findReservationResponses;
	}
}
