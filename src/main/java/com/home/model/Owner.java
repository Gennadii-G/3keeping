package com.home.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @Column(nullable = false)
    @SequenceGenerator(name = "seq_channel_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_channel_id")
    private Long id;
    private String name;
    @Column(name = "average_priority")
    private BigDecimal averagePriority;
    @Column(name = "last_month_average_priority")
    private BigDecimal lastMonthAveragePriority;

}
