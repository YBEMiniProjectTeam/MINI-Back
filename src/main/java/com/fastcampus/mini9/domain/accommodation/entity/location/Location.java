package com.fastcampus.mini9.domain.accommodation.entity.location;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;

@Embeddable
public class Location {
	@OneToOne
	private Region region;
	@OneToOne
	private District district;
}
