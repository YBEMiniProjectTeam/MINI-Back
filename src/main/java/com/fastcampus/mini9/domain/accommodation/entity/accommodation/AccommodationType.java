package com.fastcampus.mini9.domain.accommodation.entity.accommodation;

import lombok.Getter;

@Getter
public enum AccommodationType {
	HOTEL("호텔", "HOTEL"), MOTEL("모텔", "MOTEL"), PENSION("펜션", "PENSION"), RESORT("리조트", "RESORT");

	private final String korName;
	private final String engName;

	AccommodationType(String korName, String engName) {
		this.korName = korName;
		this.engName = engName;
	}

	@Override
	public String toString() {
		return engName;
	}
}
