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
    private String sector;
    private String industry;
    private String description;
    private String assetType;
    private String CIK;
    private String currency;
    private String country;
    private String address;
    private String fiscalYearEnd;
    private String latestQuarter;
    private String marketCapitalization;
    private String EBITDA;
    private String PERatio;
    private String PEGRatio;
    private String bookValue;
    private String dividendPerShare;
    private String dividendYield;
    private String EPS;
    private String revenuePerShareTTM;
    private String profitMargin;
    private String operatingMarginTTM;
    private String returnOnAssetsTTM;
    private String returnOnEquityTTM;
    private String revenueTTM;
    private String grossProfitTTM;
    private String dilutedEPSTTM;
    private String quarterlyEarningsGrowthYOY;
    private String quarterlyRevenueGrowthYOY;
    private String analystTargetPrice;
    private String analystRatingStrongBuy;
    private String analystRatingBuy;
    private String analystRatingHold;
    private String analystRatingSell;
    private String analystRatingStrongSell;
    private String trailingPE;
    private String forwardPE;
    private String priceToSalesRatioTTM;
    private String priceToBookRatio;
    private String EVToRevenue;
    private String EVToEBITDA;
    private String beta;
    private String week52High;
    private String week52Low;
    private String movingAverage50Day;
    private String movingAverage200Day;
    private String sharesOutstanding;
    private String dividendDate;
    private String exDividendDate;

}
