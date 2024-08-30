package com.nnk.springboot.domain;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
@Getter
@Setter
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_list_id")
    private Integer bidListId;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotNull(message = "Bid Quantity is mandatory")
    private Double bidQuantity;

    @NotNull(message = "Ask Quantity is mandatory")
    private Double askQuantity;

    @NotNull(message = "Bid is mandatory")
    private Double bid;

    @NotNull(message = "Ask is mandatory")
    private Double ask;

    private String benchmark;

    private Timestamp bidListDate;

    private String commentary;

    private String security;

    private String status;

    private String trader;

    private String book;

    private String creationName;

    private Timestamp creationDate;

    private String revisionName;

    private Timestamp revisionDate;

    private String dealName;

    private String dealType;

    private String sourceListId;

    private String side;
}
