package com.finance.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockDTO {

    private String name;
    private String symbol;
    private String exchange;
    private BigDecimal price;

}
