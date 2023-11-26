package com.fastcampus.mini9.domain.wish.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.mini9.common.response.BaseResponseBody;
import com.fastcampus.mini9.domain.wish.service.WishService;

@RestController
@RequestMapping("/accommodation")
public class WishController {

	private final WishService wishService;

	private WishController(WishService wishService) {
		this.wishService = wishService;
	}

	@PostMapping("/{accommodationId}/wish")
	public BaseResponseBody addWish(
		@PathVariable Long accommodationId,
		@AuthenticationPrincipal Principal principal
	) {
		wishService.addWish(accommodationId, principal.getName());
		return BaseResponseBody.success("위시 등록 완료");
	}

	@DeleteMapping("/{accommodationId}/wish")
	public BaseResponseBody deleteWish(
		@PathVariable Long accommodationId,
		@AuthenticationPrincipal Principal principal
	) {
		wishService.deleteWish(accommodationId, principal.getName());
		return BaseResponseBody.success("위시 해제 완료");
	}

}
