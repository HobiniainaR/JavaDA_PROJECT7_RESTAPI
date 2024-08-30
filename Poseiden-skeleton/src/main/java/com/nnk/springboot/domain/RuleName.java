package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rule")
@Getter
@Setter
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "name is mandatory")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "description is mandatory")
    private String description;

    @Column(name = "json")
    @NotBlank(message = "json is mandatory")
    private String json;

    @Column(name = "template")
    @NotBlank(message = "template is mandatory")
    private String template;

    @Column(name = "sql_str")
    @NotBlank(message = "sqlStr is mandatory")
    private String sqlStr;

    @Column(name = "sql_part")
    @NotBlank(message = "sqlPart is mandatory")
    private String sqlPart;
}
