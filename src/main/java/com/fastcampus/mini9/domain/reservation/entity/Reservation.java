package com.fastcampus.mini9.domain.reservation.entity;

import java.time.ZonedDateTime;

import com.fastcampus.mini9.domain.member.entity.Member;
import com.fastcampus.mini9.domain.room.entity.Room;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	private Integer numberOfGuests;
	private ZonedDateTime checkIn;
	private ZonedDateTime checkOut;
}
