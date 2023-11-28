package com.fastcampus.mini9.domain.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartIdRequest(

	@NotNull
	@Min(1)
	Long cartId) {
}
