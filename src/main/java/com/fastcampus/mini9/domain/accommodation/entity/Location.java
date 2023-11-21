package com.fastcampus.mini9.domain.accommodation.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Location {
	private Region region;
	private District district;
}
