package com.itmo.techserv.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "id_user", nullable = false)
    private Users user;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private TechService service;
    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;
    @Column (name = "cancel_sign", nullable = false)
    @ColumnDefault("false")
    private boolean cancelSign;
}
