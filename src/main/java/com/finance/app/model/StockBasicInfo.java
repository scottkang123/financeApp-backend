package com.finance.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockBasicInfo extends BaseEntity{

    private String companyName;
    private String symbol;
    private String sector;
    private String industry;
    private String exchange;

    //prevent serialization. Prevent infinite loop
    @ManyToMany(mappedBy = "stocks")
    @JsonIgnore
    private List<User> users;



}
