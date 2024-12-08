package com.itmo.techserv.repository;

import com.itmo.techserv.entity.TechService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository  extends JpaRepository<TechService,Long> {

}
