package com.fastcampus.mini9.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.mini9.domain.room.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
