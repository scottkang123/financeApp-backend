package com.finance.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock extends BaseEntity{

    private String companyName;
    private String symbol;
    private String sector;
    private String industry;
    private String exchange;

    //prevent serialization. Prevent infinite loop
    @ManyToMany(mappedBy = "stocks") // come from the user model class
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "stock")  //This comes from TradeHistory file -> private StockBasicInfo stockBasicInfo;
    private List<TradeHistory> tradeHistories;

    @OneToMany(mappedBy = "stock")
    private List<FinancialInfo> financialInfos;


}
