package com.fastcampus.mini9.domain.room.controller.dto;

import com.fastcampus.mini9.domain.room.entity.RoomDetailInfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomDetailInfoDto {

	Boolean bathTub;
	Boolean airConditioner;
	Boolean tv;
	Boolean internet;
	Boolean refrigerator;
	Boolean table;

	@Builder
	public static RoomDetailInfoDto fromEntity(RoomDetailInfo roomDetailInfo) {

		return RoomDetailInfoDto.builder()
			.bathTub(roomDetailInfo.getBathTub())
			.airConditioner(roomDetailInfo.getAirConditioner())
			.tv(roomDetailInfo.getTv())
			.internet(roomDetailInfo.getInternet())
			.refrigerator(roomDetailInfo.getRefrigerator())
			.table(roomDetailInfo.getTable())
			.build();

	}
}
