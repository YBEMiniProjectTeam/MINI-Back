package com.fastcampus.mini9.domain.accommodation.repository;

import com.fastcampus.mini9.domain.accommodation.entity.room.Room;
import com.fastcampus.mini9.domain.accommodation.entity.room.Stock;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

    boolean existsByRoomAndDate(Room room, LocalDate date);
}
