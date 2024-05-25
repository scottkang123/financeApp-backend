package com.finance.app.dto;

import com.finance.app.serializable.DTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class StockDTO implements DTO {

    private String name;
    private String symbol;
    private String exchange;
    private BigDecimal price;

}
