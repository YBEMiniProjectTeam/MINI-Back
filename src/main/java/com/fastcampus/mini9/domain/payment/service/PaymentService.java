package com.fastcampus.mini9.domain.payment.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fastcampus.mini9.domain.accommodation.entity.room.Room;
import com.fastcampus.mini9.domain.payment.dto.FindAllPaymentResponse;
import com.fastcampus.mini9.domain.payment.dto.FindSimplePaymentResponse;
import com.fastcampus.mini9.domain.payment.entity.Payment;
import com.fastcampus.mini9.domain.payment.repository.PaymentRepository;
import com.fastcampus.mini9.domain.reservation.entity.Reservation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;

	public List<FindAllPaymentResponse> findAll() {
		List<Payment> payments = paymentRepository.findAll();

		if (payments.isEmpty()) {
			return List.of();
		}
		return payments.stream().map(payment -> {
			Room room = payment.getRoom();
			Reservation reservation = payment.getReservation();

			if (room == null || reservation == null) {
				throw new NoSuchElementException();
			}
			return new FindAllPaymentResponse(room.getAccommodation().getName(),
				new FindAllPaymentResponse.RoomInfo(
					reservation.getId(), room.getName(), reservation.getCheckIn(), reservation.getCheckOut(),
					payment.getPayAt()
				));
		}).collect(Collectors.toList());
	}

	public FindSimplePaymentResponse findDetail(Long reservationId) {
		return null;
	}
}
