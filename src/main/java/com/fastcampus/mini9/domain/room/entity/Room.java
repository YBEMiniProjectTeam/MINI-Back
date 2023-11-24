package com.fastcampus.mini9.domain.room.entity;

import java.time.LocalDate;
import java.util.List;

import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "accommodation_id")
	private Accommodation accommodation;
	private String name;
	private Integer price;
	private String description;
	private int capacity;
	private int capacityMax;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int numberOfRooms;
	@OneToMany(mappedBy = "room")
	private List<Stock> stockList;
	@OneToMany(mappedBy = "room")
	private List<RoomImage> roomImageList;
	@OneToOne
	private RoomDetailInfo roomDetailInfo;

	@Builder
	Room(

		Accommodation accommodation,
		String name,
		Integer price,
		String description,
		int capacity,
		int capacityMax,
		LocalDate checkIn,
		LocalDate checkOut,
		int numberOfRooms,
		List<Stock> stockList,
		List<RoomImage> roomImageList,
		RoomDetailInfo roomDetailInfo
	) {

		this.accommodation = accommodation;
		this.name = name;
		this.price = price;
		this.description = description;
		this.capacity = capacity;
		this.capacityMax = capacityMax;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.numberOfRooms = numberOfRooms;
		this.stockList = stockList;
		this.roomImageList = roomImageList;
		this.roomDetailInfo = roomDetailInfo;

	}
}
