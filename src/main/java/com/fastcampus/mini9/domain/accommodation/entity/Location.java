package com.fastcampus.mini9.domain.accommodation.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
@Builder
public class Location {
	@ManyToOne
	private Region region;
	@ManyToOne
	private District district;

}
