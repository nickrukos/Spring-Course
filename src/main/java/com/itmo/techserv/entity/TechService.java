package com.itmo.techserv.entity;

import com.itmo.techserv.constants.TechServiceType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "techservice")
public class TechService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private TechServiceType type;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column (name = "duration")
    private int duration;
    @OneToMany(mappedBy = "service")
    private List<Booking> BookingsOfService = new ArrayList<>();
}
