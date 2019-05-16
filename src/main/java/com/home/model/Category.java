package com.home.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;
    private String name;
    private String code;
    private BigDecimal priority;
}
