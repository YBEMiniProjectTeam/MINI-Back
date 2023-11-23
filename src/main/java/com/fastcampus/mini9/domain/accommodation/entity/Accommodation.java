package com.fastcampus.mini9.domain.accommodation.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@OneToMany
	private List<AccommodationImage> accommodationImage;
	@Enumerated(EnumType.STRING)
	private AccommodationType type;
	@ManyToOne
	private Region region;
	@ManyToOne
	private District district;

	@Builder
	public Accommodation(String name, List<AccommodationImage> accommodationImage, AccommodationType type,
		Region region, District district) {
		this.name = name;
		this.accommodationImage = accommodationImage;
		this.type = type;
		this.region = region;
		this.district = district;
	}
}
