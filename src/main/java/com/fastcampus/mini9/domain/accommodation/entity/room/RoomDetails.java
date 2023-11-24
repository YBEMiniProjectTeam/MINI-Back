package com.fastcampus.mini9.domain.accommodation.entity.room;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomDetails {
	@Id
	@Column(name = "room_id")
	private Long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "room_id")
	private Room room;
}
