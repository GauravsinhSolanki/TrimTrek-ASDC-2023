package com.ProjectTrial1.Projectdemo1.hirebarber.servicezone;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceZoneRepository extends JpaRepository<ServiceZone, Integer> {
    ServiceZone findByServiceZoneId(String serviceZoneId);
}
