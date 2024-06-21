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

    @Query(value = "SELECT CONCAT(s.name, ' (', s.symbol, ')') " +
            "FROM stock s " +
            "WHERE s.peratio IS NOT NULL " +
            "AND s.peratio != 'None' " +
            "AND s.peratio != '' " +
            "AND s.peratio ~ '^[0-9]+(\\.[0-9]+)?$' " +  // Check for numeric values
            "ORDER BY CAST(s.peratio AS double precision) ASC " +
            "LIMIT 500", nativeQuery = true)
    List<Object[]> findFirst500ByOrderByPERatioAsc();


}
