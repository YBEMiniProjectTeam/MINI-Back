package com.fastcampus.mini9.domain.accommodation.entity.accommodation;

import java.util.Comparator;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import com.fastcampus.mini9.domain.accommodation.entity.location.Location;
import com.fastcampus.mini9.domain.accommodation.entity.room.Room;
import com.fastcampus.mini9.domain.accommodation.vo.AccommodationType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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

	@OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
	@BatchSize(size = 20)
	private List<AccommodationImage> images;

	@OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
	@BatchSize(size = 20)
	private List<Room> rooms;

	// TODO: 이미지 업로드 후 첫번째 사진 로드 혹은 default 이미지
	public String getThumbnail() {
		return "https://bit.ly/2Z4KKcF";
	}

	public List<String> getImagesAsString() {
		// TODO: S3에 이미지 업로드 후 DB에 url 저장
		// return images.stream().map(AccommodationImage::getUrl).collect(Collectors.toList());
		return List.of("https://bit.ly/2Z4KKcF");
	}

	public Integer getMinPrice() {
		return rooms.stream().map(Room::getPrice).min(Comparator.naturalOrder()).orElse(0);
	}

	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;
}
