package com.ProjectTrial1.Projectdemo1.hirebarber.servicezone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceZoneServices {

    @Autowired
    ServiceZoneRepository serviceZoneRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ServiceZoneServices.class);

    public List<ServiceZone> getAllServiceZones() {
        LOG.debug("getAllServiceZones : , " );
        return serviceZoneRepository.findAll();
    }

    public ServiceZone addServiceZone(ServiceZone serviceZone) {
        LOG.debug("addServiceZone serviceZone: " + serviceZone );
        return serviceZoneRepository.save(serviceZone);

    }


    public ServiceZone getServiceZoneByServiceZoneId(String serviceZoneId) {
        LOG.debug("getServiceZoneByServiceZoneId serviceZoneId: " + serviceZoneId );
        ServiceZone serviceZones = serviceZoneRepository.findByServiceZoneId(serviceZoneId);
        LOG.debug("getServiceZoneByServiceZoneId serviceZones: " + serviceZones );
        return serviceZones;
    }
}
