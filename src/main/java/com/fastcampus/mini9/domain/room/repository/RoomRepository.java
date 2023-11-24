package com.fastcampus.mini9.domain.room.repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fastcampus.mini9.domain.room.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

	Room findByCheckInBetween(ZonedDateTime checkIn, ZonedDateTime checkOut);

	List<Room> findAllByDateBetween(LocalDate checkIn, LocalDate checkOut);

	Room findByCapacityBetween(int capacity, int capacityMax);

}
