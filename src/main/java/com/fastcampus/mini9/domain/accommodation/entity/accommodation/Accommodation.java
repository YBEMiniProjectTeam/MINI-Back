package com.fastcampus.mini9.domain.accommodation.entity.accommodation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.BatchSize;

import com.fastcampus.mini9.domain.accommodation.entity.location.Location;
import com.fastcampus.mini9.domain.accommodation.entity.room.Room;
import com.fastcampus.mini9.domain.accommodation.vo.AccommodationType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"id"})
public class Accommodation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private AccommodationType type;

	@Embedded
	private Location location;

	private LocalTime checkIn;

	private LocalTime checkOut;

	@OneToOne(mappedBy = "accommodation", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private AccommodationDetails details;

	@OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
	@BatchSize(size = 20)
	private List<AccommodationImage> images = new ArrayList<>();

	@OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
	@BatchSize(size = 20)
	private List<Room> rooms;

    public String getThumbnail() {
        if (images.isEmpty()) {
            return "https://bit.ly/2Z4KKcF";
        }
        return images.get(0).getUrl();
    }

    public List<String> getImagesAsString() {
        if (images.isEmpty()) {
            return List.of("https://bit.ly/2Z4KKcF");
        }
        return images.stream()
            .map(AccommodationImage::getUrl)
            .collect(Collectors.toList());
    }

	public Integer getMinPrice() {
		return rooms.stream().map(Room::getPrice).min(Comparator.naturalOrder()).orElse(0);
	}
}
