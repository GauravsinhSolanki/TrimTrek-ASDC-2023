package com.ProjectTrial1.Projectdemo1.hirebarber.barberservice;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BarberServiceRepository extends JpaRepository<BarberService,Integer> {
    BarberService findByServiceId(String serviceId);
}
