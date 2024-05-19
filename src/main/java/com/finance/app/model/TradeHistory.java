package com.finance.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder  //Builder pattern is a design pattern that provides a way to construct complex objects step by step.  Super builder supports inheritance
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TradeHistory extends BaseEntity{

    private LocalDateTime date;
    private String EndStockPrice;
    private String StartStockPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

}
