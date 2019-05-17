package com.home.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(nullable = false)
    @SequenceGenerator(name = "seq_channel_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_channel_id")
    private Long id;
    private String name;
    private String code;
    private BigDecimal priority;
}
