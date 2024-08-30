package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trade")
@Getter
@Setter
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tradeId;

    @Column(name = "account")
    @NotBlank(message = "Account is mandatory")
    private String account;

    @Column(name = "type")
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Column(name = "buy_quantity")
    @NotNull(message = "buyQuantity is mandatory")
    private Double buyQuantity;


}
