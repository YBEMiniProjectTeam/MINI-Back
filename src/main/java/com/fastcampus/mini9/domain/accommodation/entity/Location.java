package com.fastcampus.mini9.domain.accommodation.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Location {
	private Region region;
	private District district;
}
