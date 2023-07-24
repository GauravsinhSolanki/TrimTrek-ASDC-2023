package com.ProjectTrial1.Projectdemo1.hirebarber.barberservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/barberservice")
public class BarberServiceController {
    @Autowired
    BarberServiceServices barberServiceServices;

    @GetMapping("/all-barber-services")
    public List<BarberService> getAllBarberServices() {
        return barberServiceServices.getAllBarberServices();
    }


    @PostMapping("/add-barber-service")
    public BarberService addBarberService(@RequestBody BarberService barberService) {
        return barberServiceServices.addBarberService(barberService);
    }

    @GetMapping("/{serviceId}")
    public BarberService getBarberServiceByServiceId(@PathVariable String serviceId) {
        return barberServiceServices.getBarberServiceByServiceId(serviceId);
    }

}
