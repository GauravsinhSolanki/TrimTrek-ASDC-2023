package com.ProjectTrial1.Projectdemo1.hirebarber.servicezone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ServiceZoneController {

    @Autowired
    private ServiceZoneServices serviceZoneServices;

    @GetMapping("/all-service-zones")
    public List<ServiceZone> getAllServiceZones() {
        return serviceZoneServices.getAllServiceZones();
    }

    @PostMapping("/add-service-zone")
    public ServiceZone addServiceZone(@RequestBody ServiceZone serviceZone) {
        return serviceZoneServices.addServiceZone(serviceZone);
    }

    @GetMapping("/service-zone/{serviceZoneId}")
    public ServiceZone getServiceZoneByServiceZoneId(@PathVariable String serviceZoneId) {
        return serviceZoneServices.getServiceZoneByServiceZoneId(serviceZoneId);
    }
}
