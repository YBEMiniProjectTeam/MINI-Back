package com.fastcampus.mini9.domain.payment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.mini9.common.response.DataResponseBody;
import com.fastcampus.mini9.domain.payment.dto.FindAllPaymentResponse;
import com.fastcampus.mini9.domain.payment.dto.FindSimplePaymentResponse;
import com.fastcampus.mini9.domain.payment.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentService paymentService;

	@Operation(summary = "예약, 결제 전체 내역 조회")
	@GetMapping
	public DataResponseBody<List<FindAllPaymentResponse>> findAll() {
		return DataResponseBody.success(paymentService.findAll());
	}

	@Operation(summary = "예약, 결제 상세 내역 조회")
	@GetMapping("/{reservationId}")
	public DataResponseBody<FindSimplePaymentResponse> findDetail(@PathVariable Long reservationId) {
		return DataResponseBody.success(paymentService.findDetail(reservationId));
	}
}
