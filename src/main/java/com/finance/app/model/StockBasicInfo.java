package com.finance.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
public class StockBasicInfo {

    private Integer id;
    private String companyName;
    private String symbol;
    private String sector;
    private String industry;
    private String exchange;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

}
