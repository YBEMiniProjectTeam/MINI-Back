package com.fastcampus.mini9.domain.accommodation.entity;

import java.util.List;

import com.fastcampus.mini9.domain.room.entity.Room;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Accommodation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "accommodation")
	private List<AccommodationImage> accommodationImage;
	@Enumerated(EnumType.STRING)
	private AccommodationType type;
	@Embedded
	private Location location;
	@OneToMany(mappedBy = "accommodation")
	private List<Room> rooms;
	@OneToOne
	private AccommodationDetailInfo accommodationDetailInfo;

	@Builder
	public Accommodation(String name, List<AccommodationImage> accommodationImage, AccommodationType type,
		Location location, List<Room> rooms, AccommodationDetailInfo accommodationDetailInfo) {
		this.name = name;
		this.accommodationImage = accommodationImage;
		this.type = type;
		this.location = location;
		this.rooms = rooms;
		this.accommodationDetailInfo = accommodationDetailInfo;

	}
}
