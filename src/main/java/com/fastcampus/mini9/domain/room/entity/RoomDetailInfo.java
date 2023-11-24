package com.fastcampus.mini9.domain.room.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomDetailInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean bathTub;
	private Boolean airConditioner;
	private Boolean tv;
	private Boolean internet;
	private Boolean refrigerator;
	private Boolean table;
	@OneToOne
	private Room room;

	@Builder
	public RoomDetailInfo(
		Boolean bathTub,
		Boolean airConditioner,
		Boolean tv,
		Boolean internet,
		Boolean refrigerator,
		Boolean table,
		Room room
	) {
		this.bathTub = bathTub;
		this.airConditioner = airConditioner;
		this.tv = tv;
		this.internet = internet;
		this.refrigerator = refrigerator;
		this.table = table;
		this.room = room;
	}

}
