package com.fastcampus.mini9.domain.room.entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
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
	private ZonedDateTime checkIn;
	private ZonedDateTime checkOut;
	private int numberOfRooms;
	@OneToMany(mappedBy = "room")
	private List<Stock> stockList = new ArrayList<>();
}
