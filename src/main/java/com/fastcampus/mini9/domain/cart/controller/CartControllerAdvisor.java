package com.fastcampus.mini9.domain.cart.controller;

import com.fastcampus.mini9.common.response.ErrorResponseBody;
import com.fastcampus.mini9.domain.cart.service.OutOfStockException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CartControllerAdvisor {
	@ExceptionHandler(OutOfStockException.class)
	public ErrorResponseBody outOfStock(OutOfStockException outOfStockException) {
		return ErrorResponseBody.unsuccessful(400, outOfStockException.getMessage());
	}
}
