package com.fastcampus.mini9.domain.accommodation.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fastcampus.mini9.domain.accommodation.entity.room.Room;
import com.fastcampus.mini9.domain.accommodation.entity.room.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

	boolean existsByRoomAndDate(Room room, LocalDate date);

	@Modifying
	@Query("DELETE FROM Stock s WHERE s.date < :date")
	void deleteStocksBeforeDate(@Param("date") LocalDate date);

	List<Stock> findByRoomAndDateBetween(Room targetRoom, LocalDate checkInDate, LocalDate checkOutDate);
}
