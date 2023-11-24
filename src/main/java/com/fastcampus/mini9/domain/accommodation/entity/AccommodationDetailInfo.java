package com.fastcampus.mini9.domain.accommodation.entity;

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
public class AccommodationDetailInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String latitude;
	private String longitude;
	private String phoneNum;
	private Boolean parkingSpace;
	private Boolean cookingArea;
	@OneToOne
	private Accommodation accommodation;

	@Builder
	public AccommodationDetailInfo(
		String description,
		String latitude,
		String longitude,
		String phoneNum,
		Boolean parkingSpace,
		Boolean cookingArea
	) {
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phoneNum = phoneNum;
		this.parkingSpace = parkingSpace;
		this.cookingArea = cookingArea;

	}

}
