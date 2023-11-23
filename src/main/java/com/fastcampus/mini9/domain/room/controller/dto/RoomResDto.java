package com.fastcampus.mini9.domain.room.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RoomResDto {
	Long accommodationId;
	String startDate;
	String endDate;
	String guestNum;
}
