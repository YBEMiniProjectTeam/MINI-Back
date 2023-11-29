package com.fastcampus.mini9.domain.accommodation.repository;

import com.fastcampus.mini9.domain.accommodation.entity.room.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
