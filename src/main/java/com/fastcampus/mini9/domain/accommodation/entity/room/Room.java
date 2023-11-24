package com.fastcampus.mini9.domain.accommodation.entity.room;

import java.util.ArrayList;
import java.util.List;

import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
	@OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private RoomDetails details;
	private String name;
	private Integer price;
	private int capacity;
	private int capacityMax;
	private int numberOfRoom;
	@OneToMany(mappedBy = "room")
	private List<Stock> stockList = new ArrayList<>();
}
