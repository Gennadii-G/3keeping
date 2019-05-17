package com.home.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "note")
public class Note {

    @Id
    @Column(nullable = false)
    @SequenceGenerator(name = "seq_channel_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_channel_id")
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate noteDate;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    private Category category;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @NotNull
    private Owner owner;

}
