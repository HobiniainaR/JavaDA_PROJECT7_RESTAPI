package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
@Getter
@Setter
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "curve_id")
    private Integer curveId;

    @Column(name = "as_of_date")
    private Timestamp asOfDate;

    @NotNull(message = "term is mandatory")
    @Column(name = "term")
    private Double term;

    @NotNull(message = "value is mandatory")
    @Column(name = "curve_value")
    private Double value;

    @Column(name = "creation_date")
    private Timestamp creationDate;
}