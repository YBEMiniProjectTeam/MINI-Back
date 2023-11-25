package com.fastcampus.mini9.domain.accommodation.entity.location;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Location {
	@ManyToOne
	private Region region;
	@ManyToOne
	private District district;
}
