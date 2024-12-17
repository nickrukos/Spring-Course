package com.itmo.techserv.entity;

import com.itmo.techserv.constants.TechServiceType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "techservice")
public class TechService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private TechServiceType type;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "description",nullable = false)
    private String description;
    @Column (name = "duration",nullable = false)
    private int duration;
    @Column(name = "value", nullable = false)
    private long value;
    @OneToMany(mappedBy = "service")
    private List<Booking> BookingsOfService = new ArrayList<>();
}
