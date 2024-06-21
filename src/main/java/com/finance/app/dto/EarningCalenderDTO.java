package com.finance.app.dto;
import com.finance.app.serializable.DTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class EarningCalenderDTO implements DTO{
    private String symbol;
    private String name;
    private String reportDate;
    private String fiscalDateEnding;
    private String estimate;
    private String currency;

    @Override
    public String toString() {
        return "EarningCalenderDTO{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", reportDate='" + reportDate + '\'' +
                ", fiscalDateEnding='" + fiscalDateEnding + '\'' +
                ", estimate='" + estimate + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
