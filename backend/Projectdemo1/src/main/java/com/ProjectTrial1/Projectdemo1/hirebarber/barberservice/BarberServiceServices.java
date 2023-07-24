package com.ProjectTrial1.Projectdemo1.hirebarber.barberservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberServiceServices {

    @Autowired
    BarberServiceRepository barberServiceRepository;

    private static final Logger LOG = LoggerFactory.getLogger(BarberServiceServices.class);
    public List<BarberService> getAllBarberServices() {
        List<BarberService> list = barberServiceRepository.findAll();
        LOG.debug("getAllBarberServices list: " + list);
        return list;
    }


    public BarberService addBarberService(BarberService barberService) {
        LOG.debug("addServiceRequest serviceRequestEntity: " + barberService);
        return barberServiceRepository.save(barberService);
    }

    public BarberService getBarberServiceByServiceId(String serviceId) {
        LOG.debug("getServiceByServiceRequestId serviceRequestId: " + serviceId);
        BarberService barberService = barberServiceRepository.findByServiceId(serviceId);
        LOG.debug("getServiceByServiceRequestId serviceRequestId: " + serviceId);
        return barberService;
    }


}
