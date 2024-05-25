package com.finance.app.transformer;

import com.finance.app.dto.StockDTO;
import com.finance.app.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockTransformer implements Transformer<Stock, StockDTO> {
    @Override
    public Stock transformDtoM(StockDTO dto) {
        if (dto == null) {
            return null;
        }

        // TODO: Basic transform logic implemented for now
        return Stock.builder()
                .name(dto.getName())
                .symbol(dto.getSymbol())
                .exchange(dto.getExchange())
                .price(dto.getPrice())
                .build();
    }

    @Override
    public StockDTO transformMtoD(Stock stock) {
        if (stock == null) {
            return null;
        }

        return StockDTO.builder()
                .name(stock.getName())
                .symbol(stock.getSymbol())
                .exchange(stock.getExchange())
                .price(stock.getPrice())
                .build();
    }
}
