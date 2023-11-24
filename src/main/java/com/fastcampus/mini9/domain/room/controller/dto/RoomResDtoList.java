package com.fastcampus.mini9.domain.room.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fastcampus.mini9.domain.room.entity.Room;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RoomResDtoList {

	List<RoomResDto> roomResDtoList;

	@Getter
	@Builder
	public static class RoomResDto {

		private Long id;
		private String name;
		private Integer price;
		private String description;
		private int capacity;
		private int capacity_max;
		private int stock_quantity;
		private List<RoomImageDto> room_image;

		@Builder
		public static RoomResDto fromEntity(Room room) {
			return RoomResDto.builder()
				.id(room.getId())
				.name(room.getName())
				.price(room.getPrice())
				.description(room.getDescription())
				.capacity(room.getCapacity())
				.capacity_max(room.getCapacityMax())
				.stock_quantity(room.getStockList().size())
				.room_image(room.getRoomImageList()
					.stream().map(RoomImageDto::fromEntity)
					.collect(Collectors.toList())
				)
				.build();

		}
	}

}

// {
// 	"status": "SUCCESS",
// 	"data": {
// 	"rooms": [
// 	{
// 	"id": 1,
// 	"name": "민들레실",
// 	"price": 100000,
// 	"capacity": 2,
// 	"capacity_max": 4,
// 	"room_quantity": 2,
// 	"room_image": [
// 	{
// 	"url": "https://bit.ly/2Z4KKcF"
// 	},
// 	{
// 	"url": "https://bit.ly/2Z4KKcF"
// 	}
// 	]
// 	},
// 	{
// 	"id": 2,
// 	"name": "장미실",
// 	"price": 100000,
// 	"capacity": 2,
// 	"capacity_max": 4,
// 	"room_quantity": 3,
// 	"room_image": [
// 	{
// 	"url": "https://bit.ly/2Z4KKcF"
// 	},
// 	{
// 	"url": "https://bit.ly/2Z4KKcF"
// 	}
// 	]
// 	}
// 	]
// 	}
// 	}