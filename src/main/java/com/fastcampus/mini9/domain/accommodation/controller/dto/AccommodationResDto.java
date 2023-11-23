package com.fastcampus.mini9.domain.accommodation.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;
import com.fastcampus.mini9.domain.accommodation.entity.AccommodationImage;
import com.fastcampus.mini9.domain.accommodation.entity.AccommodationType;
import com.fastcampus.mini9.domain.accommodation.entity.District;
import com.fastcampus.mini9.domain.accommodation.entity.Location;
import com.fastcampus.mini9.domain.accommodation.entity.Region;

import lombok.Builder;
import lombok.Getter;

public class AccommodationResDto {

	@Getter
	@Builder
	public static class OriginAccommodationDto {

		private Long id;
		private String name;
		private AccommodationType type;
		private DistrictDto district;
		private RegionDto region;
		private List<accommodationImageDto> accommodationImage;

		public static OriginAccommodationDto fromEntity(Accommodation accommodation) {
			return OriginAccommodationDto.builder()
				.id(accommodation.getId())
				.name(accommodation.getName())
				.type(accommodation.getType())
				.district(DistrictDto.fromEntity(accommodation.getDistrict()))
				.region(RegionDto.fromEntity(accommodation.getRegion()))
				.accommodationImage(
					accommodation.getAccommodationImage().stream()
						.map(accommodationImageDto::fromEntity)
						.collect(Collectors.toList())
				)
				.build();

		}

	}

	@Getter
	@Builder
	public static class LocationDto {

		private RegionDto region;
		private DistrictDto district;

		public static LocationDto fromEntity(Location location) {

			return LocationDto.builder()
				.region(RegionDto.fromEntity(location.getRegion()))
				.district(DistrictDto.fromEntity(location.getDistrict()))
				.build();
		}

	}

	@Getter
	@Builder
	public static class accommodationImageDto {

		private String url;

		public static accommodationImageDto fromEntity(AccommodationImage accommodationImage) {
			return accommodationImageDto.builder()
				.url(accommodationImage.getUrl())
				.build();
		}

	}

	@Getter
	@Builder
	public static class RegionDto {
		private String name;

		public static RegionDto fromEntity(Region region) {
			return RegionDto.builder()
				.name(region.getName())
				.build();
		}
	}

	@Getter
	@Builder
	public static class DistrictDto {
		private String name;

		public static DistrictDto fromEntity(District district) {
			return DistrictDto.builder()
				.name(district.getName())
				.build();
		}
	}

}
