package com.fastcampus.mini9.domain.cart.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartIdsRequest(

	@NotNull
	@Min(1)
	List<Long> cartIds) {
}
