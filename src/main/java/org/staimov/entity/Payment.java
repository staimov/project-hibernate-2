package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Payment extends UpdateDetails {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="staff_id", nullable = false)
    private Staff staff;

    @OneToOne
    @JoinColumn(name="rental_id")
    private Rental rental;

    @Column(name="amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;
}
