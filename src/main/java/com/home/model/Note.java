package com.home.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate noteDate;

}
