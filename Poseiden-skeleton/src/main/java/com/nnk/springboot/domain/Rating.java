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
@Table(name = "rating")
@Getter
@Setter
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Moody's Rating is mandatory")
    private String moodysRating;

    @NotBlank(message = "S&P Rating is mandatory")
    private String sandPRating;

    @NotBlank(message = "Fitch Rating is mandatory")
    private String fitchRating;

    @NotNull
    @Column(name = "order_number")
    private Integer orderNumber;
}
