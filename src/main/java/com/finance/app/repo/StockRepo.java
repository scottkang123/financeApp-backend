package com.finance.app.repo;

import com.finance.app.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepo extends JpaRepository<Stock, Long> {
    @Query("SELECT stock FROM Stock stock WHERE LOWER(stock.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Stock> findbyNameContainingKeywordIgnoreCase(@Param("keyword") String keyword);

    Stock findBySymbol(String symbol);

    @Query("SELECT s.symbol FROM Stock s")
    List<String> findAllSymbols();

}
