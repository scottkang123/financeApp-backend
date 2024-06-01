package com.finance.app.transformer;

import com.finance.app.dto.StockDTO;
import com.finance.app.model.Stock;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Map;

import static com.nimbusds.jose.util.JSONObjectUtils.getString;

@Component
public class StockTransformer implements Transformer<Stock, StockDTO> {

    public static StockDTO mapJsonToStockDTO(Map<String, Object> jsonMap) throws ParseException {
        return StockDTO.builder()
                .name(getString(jsonMap, "Name"))
                .symbol(getString(jsonMap, "Symbol"))
                .exchange(getString(jsonMap, "Exchange"))
                .sector(getString(jsonMap, "Sector"))
                .industry(getString(jsonMap, "Industry"))
                .description(getString(jsonMap, "Description"))
                .assetType(getString(jsonMap, "AssetType"))
                .CIK(getString(jsonMap, "CIK"))
                .currency(getString(jsonMap, "Currency"))
                .country(getString(jsonMap, "Country"))
                .address(getString(jsonMap, "Address"))
                .fiscalYearEnd(getString(jsonMap, "FiscalYearEnd"))
                .latestQuarter(getString(jsonMap, "LatestQuarter"))
                .marketCapitalization(getString(jsonMap, "MarketCapitalization"))
                .EBITDA(getString(jsonMap, "EBITDA"))
                .PERatio(getString(jsonMap, "PERatio"))
                .PEGRatio(getString(jsonMap, "PEGRatio"))
                .bookValue(getString(jsonMap, "BookValue"))
                .dividendPerShare(getString(jsonMap, "DividendPerShare"))
                .dividendYield(getString(jsonMap, "DividendYield"))
                .EPS(getString(jsonMap, "EPS"))
                .revenuePerShareTTM(getString(jsonMap, "RevenuePerShareTTM"))
                .profitMargin(getString(jsonMap, "ProfitMargin"))
                .operatingMarginTTM(getString(jsonMap, "OperatingMarginTTM"))
                .returnOnAssetsTTM(getString(jsonMap, "ReturnOnAssetsTTM"))
                .returnOnEquityTTM(getString(jsonMap, "ReturnOnEquityTTM"))
                .revenueTTM(getString(jsonMap, "RevenueTTM"))
                .grossProfitTTM(getString(jsonMap, "GrossProfitTTM"))
                .dilutedEPSTTM(getString(jsonMap, "DilutedEPSTTM"))
                .quarterlyEarningsGrowthYOY(getString(jsonMap, "QuarterlyEarningsGrowthYOY"))
                .quarterlyRevenueGrowthYOY(getString(jsonMap, "QuarterlyRevenueGrowthYOY"))
                .analystTargetPrice(getString(jsonMap, "AnalystTargetPrice"))
                .analystRatingStrongBuy(getString(jsonMap, "AnalystRatingStrongBuy"))
                .analystRatingBuy(getString(jsonMap, "AnalystRatingBuy"))
                .analystRatingHold(getString(jsonMap, "AnalystRatingHold"))
                .analystRatingSell(getString(jsonMap, "AnalystRatingSell"))
                .analystRatingStrongSell(getString(jsonMap, "AnalystRatingStrongSell"))
                .trailingPE(getString(jsonMap, "TrailingPE"))
                .forwardPE(getString(jsonMap, "ForwardPE"))
                .priceToSalesRatioTTM(getString(jsonMap, "PriceToSalesRatioTTM"))
                .priceToBookRatio(getString(jsonMap, "PriceToBookRatio"))
                .EVToRevenue(getString(jsonMap, "EVToRevenue"))
                .EVToEBITDA(getString(jsonMap, "EVToEBITDA"))
                .beta(getString(jsonMap, "Beta"))
                .week52High(getString(jsonMap, "52WeekHigh"))
                .week52Low(getString(jsonMap, "52WeekLow"))
                .movingAverage50Day(getString(jsonMap, "50DayMovingAverage"))
                .movingAverage200Day(getString(jsonMap, "200DayMovingAverage"))
                .sharesOutstanding(getString(jsonMap, "SharesOutstanding"))
                .dividendDate(getString(jsonMap, "DividendDate"))
                .exDividendDate(getString(jsonMap, "ExDividendDate"))
                .build();

    }

    private static String getString(Map<String, Object> jsonMap, String key) {
        String value = (String) jsonMap.get(key);
        return (value != null && !value.isEmpty()) ? value : null;
    }

    @Override
    public Stock transformDtoM(StockDTO dto) {
        if (dto == null) {
            return null;
        }

        Stock stock = new Stock();
        stock.setName(dto.getName());
        stock.setSymbol(dto.getSymbol());
        stock.setSector(dto.getSector());
        stock.setIndustry(dto.getIndustry());
        stock.setExchange(dto.getExchange());
        stock.setDescription(dto.getDescription());
        stock.setAssetType(dto.getAssetType());
        stock.setCIK(dto.getCIK());
        stock.setCurrency(dto.getCurrency());
        stock.setCountry(dto.getCountry());
        stock.setAddress(dto.getAddress());
        stock.setFiscalYearEnd(dto.getFiscalYearEnd());
        stock.setLatestQuarter(dto.getLatestQuarter());
        stock.setMarketCapitalization(dto.getMarketCapitalization());
        stock.setEBITDA(dto.getEBITDA());
        stock.setPERatio(dto.getPERatio());
        stock.setPEGRatio(dto.getPEGRatio());
        stock.setBookValue(dto.getBookValue());
        stock.setDividendPerShare(dto.getDividendPerShare());
        stock.setDividendYield(dto.getDividendYield());
        stock.setEPS(dto.getEPS());
        stock.setRevenuePerShareTTM(dto.getRevenuePerShareTTM());
        stock.setProfitMargin(dto.getProfitMargin());
        stock.setOperatingMarginTTM(dto.getOperatingMarginTTM());
        stock.setReturnOnAssetsTTM(dto.getReturnOnAssetsTTM());
        stock.setReturnOnEquityTTM(dto.getReturnOnEquityTTM());
        stock.setRevenueTTM(dto.getRevenueTTM());
        stock.setGrossProfitTTM(dto.getGrossProfitTTM());
        stock.setDilutedEPSTTM(dto.getDilutedEPSTTM());
        stock.setQuarterlyEarningsGrowthYOY(dto.getQuarterlyEarningsGrowthYOY());
        stock.setQuarterlyRevenueGrowthYOY(dto.getQuarterlyRevenueGrowthYOY());
        stock.setAnalystTargetPrice(dto.getAnalystTargetPrice());
        stock.setAnalystRatingStrongBuy(dto.getAnalystRatingStrongBuy());
        stock.setAnalystRatingBuy(dto.getAnalystRatingBuy());
        stock.setAnalystRatingHold(dto.getAnalystRatingHold());
        stock.setAnalystRatingSell(dto.getAnalystRatingSell());
        stock.setAnalystRatingStrongSell(dto.getAnalystRatingStrongSell());
        stock.setTrailingPE(dto.getTrailingPE());
        stock.setForwardPE(dto.getForwardPE());
        stock.setPriceToSalesRatioTTM(dto.getPriceToSalesRatioTTM());
        stock.setPriceToBookRatio(dto.getPriceToBookRatio());
        stock.setEVToRevenue(dto.getEVToRevenue());
        stock.setEVToEBITDA(dto.getEVToEBITDA());
        stock.setBeta(dto.getBeta());
        stock.setWeek52High(dto.getWeek52High());
        stock.setWeek52Low(dto.getWeek52Low());
        stock.setMovingAverage50Day(dto.getMovingAverage50Day());
        stock.setMovingAverage200Day(dto.getMovingAverage200Day());
        stock.setSharesOutstanding(dto.getSharesOutstanding());
        stock.setDividendDate(dto.getDividendDate());
        stock.setExDividendDate(dto.getExDividendDate());

        return stock;
    }

    @Override
    public StockDTO transformMtoD(Stock stock) {
        if (stock == null) {
            return null;
        }

        // Create a new StockDTO object using its builder and set its properties
        return StockDTO.builder()
                .name(stock.getName())
                .symbol(stock.getSymbol())
                .exchange(stock.getExchange())
                .sector(stock.getSector())
                .industry(stock.getIndustry())
                .description(stock.getDescription())
                .assetType(stock.getAssetType())
                .CIK(stock.getCIK())
                .currency(stock.getCurrency())
                .country(stock.getCountry())
                .address(stock.getAddress())
                .fiscalYearEnd(stock.getFiscalYearEnd())
                .latestQuarter(stock.getLatestQuarter())
                .marketCapitalization(stock.getMarketCapitalization())
                .EBITDA(stock.getEBITDA())
                .PERatio(stock.getPERatio())
                .PEGRatio(stock.getPEGRatio())
                .bookValue(stock.getBookValue())
                .dividendPerShare(stock.getDividendPerShare())
                .dividendYield(stock.getDividendYield())
                .EPS(stock.getEPS())
                .revenuePerShareTTM(stock.getRevenuePerShareTTM())
                .profitMargin(stock.getProfitMargin())
                .operatingMarginTTM(stock.getOperatingMarginTTM())
                .returnOnAssetsTTM(stock.getReturnOnAssetsTTM())
                .returnOnEquityTTM(stock.getReturnOnEquityTTM())
                .revenueTTM(stock.getRevenueTTM())
                .grossProfitTTM(stock.getGrossProfitTTM())
                .dilutedEPSTTM(stock.getDilutedEPSTTM())
                .quarterlyEarningsGrowthYOY(stock.getQuarterlyEarningsGrowthYOY())
                .quarterlyRevenueGrowthYOY(stock.getQuarterlyRevenueGrowthYOY())
                .analystTargetPrice(stock.getAnalystTargetPrice())
                .analystRatingStrongBuy(stock.getAnalystRatingStrongBuy())
                .analystRatingBuy(stock.getAnalystRatingBuy())
                .analystRatingHold(stock.getAnalystRatingHold())
                .analystRatingSell(stock.getAnalystRatingSell())
                .analystRatingStrongSell(stock.getAnalystRatingStrongSell())
                .trailingPE(stock.getTrailingPE())
                .forwardPE(stock.getForwardPE())
                .priceToSalesRatioTTM(stock.getPriceToSalesRatioTTM())
                .priceToBookRatio(stock.getPriceToBookRatio())
                .EVToRevenue(stock.getEVToRevenue())
                .EVToEBITDA(stock.getEVToEBITDA())
                .beta(stock.getBeta())
                .week52High(stock.getWeek52High())
                .week52Low(stock.getWeek52Low())
                .movingAverage50Day(stock.getMovingAverage50Day())
                .movingAverage200Day(stock.getMovingAverage200Day())
                .sharesOutstanding(stock.getSharesOutstanding())
                .dividendDate(stock.getDividendDate())
                .exDividendDate(stock.getExDividendDate())
                .build();
    }
}