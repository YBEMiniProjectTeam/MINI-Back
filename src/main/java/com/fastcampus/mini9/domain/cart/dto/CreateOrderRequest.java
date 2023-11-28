package com.fastcampus.mini9.domain.cart.dto;

import java.util.List;

public record CreateOrderRequest(

	List<Long> cartIds,
	String reservationName) {

}
