package com.fastcampus.mini9.common.util.schduler;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockScheduler {

    private static final int INIT_MONTH = 3;

    private final StockService stockService;

    @Async
    @PostConstruct
    public void initStock() {
        stockService.createStocks(LocalDate.now(), LocalDate.now().plusMonths(INIT_MONTH));
    }

    @Async
    @Scheduled(cron = "0 0 0 * * *")
    public void dailyStock() {
        stockService.createStocks(LocalDate.now().plusMonths(INIT_MONTH));
        stockService.deleteBeforeStock(LocalDate.now());
    }
}
