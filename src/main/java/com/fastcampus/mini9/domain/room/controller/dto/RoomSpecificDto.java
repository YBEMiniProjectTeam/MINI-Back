package com.fastcampus.mini9.domain.room.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fastcampus.mini9.domain.room.entity.Room;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomSpecificDto {
	private Long id;
	private String name;
	private Integer price;
	private String intro_message;
	private int capacity;
	private int capacity_max;
	private int stock_quantity;
	private List<RoomImageDto> room_image;
	private RoomDetailInfoDto room_detail_info;

	public static RoomSpecificDto fromEntity(Room room) {

		return RoomSpecificDto.builder()
			.id(room.getId())
			.name(room.getName())
			.price(room.getPrice())
			.intro_message(room.getDescription())
			.capacity(room.getCapacity())
			.capacity_max(room.getCapacityMax())
			.stock_quantity(room.getStockList().size())
			.room_image(room.getRoomImageList()
				.stream()
				.map(RoomImageDto::fromEntity)
				.collect(Collectors.toList())
			)
			.room_detail_info(RoomDetailInfoDto.fromEntity(room.getRoomDetailInfo()))
			.build();

	}
}

// {
// 	"status": "SUCCESS",
// 	"data": {
// 	"id": 1,
// 	"name": "민들레실",
// 	"price": 10000,
// 	"capacity": 2,
// 	"capacity_max": 4,
// 	"intro_message": "1번 객실입니다. 좋아요",
// 	"stock_quantity": 3,
// 	"room_image": [
// 	{
// 	"url": "https://bit.ly/2Z4KKcF"
// 	},
// 	{
// 	"url": "https://bit.ly/2Z4KKcF"
// 	}
// 	]
// 	}
// 	}