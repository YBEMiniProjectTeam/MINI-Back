package com.fastcampus.mini9.domain.accommodation.entity.accommodation;

import com.fastcampus.mini9.domain.accommodation.entity.location.Location;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
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
	@Enumerated(EnumType.STRING)
	private AccommodationType type;
	@Embedded
	private Location location;
	private String checkIn;
	private String checkOut;
	@OneToOne(mappedBy = "accommodation", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private AccommodationDetails details;
}
