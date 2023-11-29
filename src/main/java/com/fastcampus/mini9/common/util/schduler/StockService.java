package com.fastcampus.mini9.common.util.schduler;

import com.fastcampus.mini9.domain.accommodation.entity.room.Room;
import com.fastcampus.mini9.domain.accommodation.entity.room.Stock;
import com.fastcampus.mini9.domain.accommodation.repository.RoomRepository;
import com.fastcampus.mini9.domain.accommodation.repository.StockRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StockService {

    private final RoomRepository roomRepository;

    private final StockRepository stockRepository;

    @Async
    public void createStocks(LocalDate startDate, LocalDate endDate) {
        List<Room> rooms = roomRepository.findAll();
        rooms.parallelStream().forEach(room ->
            startDate.datesUntil(endDate).forEach(date ->
                createStockForDateWithoutExistsCheck(room, date)));
    }

    @Async
    public void createStocks(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(1);
        List<Room> rooms = roomRepository.findAll();
        rooms.parallelStream().forEach(room ->
            startDate.datesUntil(endDate).forEach(date ->
                createStockForDate(room, date)));
    }

    @Async
    public void createStockForDate(Room room, LocalDate date) {
        if (!stockExists(room, date)) {
            Stock stock = Stock.builder()
                .room(room)
                .date(date)
                .quantity(room.getNumberOfRoom())
                .build();
            stockRepository.save(stock);
        }
    }

    @Async
    public void createStockForDateWithoutExistsCheck(Room room, LocalDate date) {
        Stock stock = Stock.builder()
            .room(room)
            .date(date)
            .quantity(room.getNumberOfRoom())
            .build();
        stockRepository.save(stock);
    }

    private boolean stockExists(Room room, LocalDate date) {
        return stockRepository.existsByRoomAndDate(room, date);
    }

    public void deleteBeforeStock(LocalDate date) {
        stockRepository.deleteStocksBeforeDate(date);
    }
}
