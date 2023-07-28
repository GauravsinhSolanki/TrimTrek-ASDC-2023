package com.ProjectTrial1.Projectdemo1.hirebarber.servicezone;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class ServiceZoneServicesTest {
    @Mock
    ServiceZoneRepository serviceZoneRepository;

    @InjectMocks
    ServiceZoneServices serviceZoneServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllServiceZones() {
        when(serviceZoneRepository.findAll()).thenReturn(Arrays.asList(new ServiceZone(), new ServiceZone()));
        List<ServiceZone> result = serviceZoneServices.getAllServiceZones();
        verify(serviceZoneRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testAddServiceZone() {
        ServiceZone serviceZone = new ServiceZone();
        serviceZone.setServiceZoneId("1");
        when(serviceZoneRepository.save(any(ServiceZone.class))).thenReturn(serviceZone);
        ServiceZone result = serviceZoneServices.addServiceZone(serviceZone);
        verify(serviceZoneRepository, times(1)).save(any(ServiceZone.class));
        assertEquals("1", result.getServiceZoneId());
    }

    @Test
    public void testGetServiceZoneByServiceZoneId() {
        ServiceZone serviceZone = new ServiceZone();
        serviceZone.setServiceZoneId("1");
        when(serviceZoneRepository.findByServiceZoneId(anyString())).thenReturn(serviceZone);
        ServiceZone result = serviceZoneServices.getServiceZoneByServiceZoneId("1");
        verify(serviceZoneRepository, times(1)).findByServiceZoneId("1");
        assertEquals("1", result.getServiceZoneId());
    }
}
