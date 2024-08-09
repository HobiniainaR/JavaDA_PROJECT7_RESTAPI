package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
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

    @Column(name = "as_of_date")
    private Timestamp asOfDate;

    @NotNull(message = "Term is mandatory")
    @Digits(integer = 10, fraction = 2, message = "Term must be a number with up to 2 decimal places")
    private Double term;

    @NotNull(message = "Value is mandatory")
    @Digits(integer = 10, fraction = 2, message = "Value must be a number with up to 2 decimal places")
    private Double value;

    @Column(name = "creation_date")
    private Timestamp creationDate;
}
