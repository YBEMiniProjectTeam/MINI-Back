package com.fastcampus.mini9.domain.accommodation.entity.room;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;
import com.fastcampus.mini9.domain.payment.entity.Payment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accommodation_id")
	private Accommodation accommodation;

	@OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private RoomDetails details;

	private String name;

	private Integer price;

	private String description;

	private int capacity;

	private int capacityMax;

	private int numberOfRoom;

	@OneToMany(mappedBy = "room")
	private List<Stock> stockList = new ArrayList<>();

	@OneToMany(mappedBy = "room")
	private List<Payment> payments = new ArrayList<>();

	private LocalTime checkIn;

	private LocalTime checkOut;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Stock> stocks = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Stock> rooms = new ArrayList<>();

	public boolean isOverCapacityMax(int numberOfGuest) {
		return this.capacityMax < numberOfGuest;
	}

	public boolean isSameTotalPrice(int totalPrice, long betweenDay) {
		return this.price != null && this.price * betweenDay == totalPrice;
	}

	public boolean isNotEnoughStockAtCheckDate(ZonedDateTime checkIn, ZonedDateTime checkOut) {
		return false;
	}

	public void calcStock(ZonedDateTime checkIn, ZonedDateTime checkOut, int i) {
	}
}
