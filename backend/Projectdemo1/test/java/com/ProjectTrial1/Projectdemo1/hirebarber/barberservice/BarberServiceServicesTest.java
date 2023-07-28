package com.ProjectTrial1.Projectdemo1.hirebarber.barberservice;

import static org.junit.jupiter.api.Assertions.*;
import com.ProjectTrial1.Projectdemo1.hirebarber.barberservice.BarberService;
import com.ProjectTrial1.Projectdemo1.hirebarber.barberservice.BarberServiceJdbcRepository;
import com.ProjectTrial1.Projectdemo1.hirebarber.barberservice.BarberServiceServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BarberServiceServicesTest {

    @Mock
    BarberServiceJdbcRepository barberServiceJdbcRepository;

    @InjectMocks
    BarberServiceServices barberServiceServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBarberServices() {
        BarberService barberService1 = new BarberService();
        barberService1.setServiceId("service1");
        BarberService barberService2 = new BarberService();
        barberService2.setServiceId("service2");

        when(barberServiceJdbcRepository.getAllBarberServices()).thenReturn(Arrays.asList(barberService1, barberService2));

        List<BarberService> result = barberServiceServices.getAllBarberServices();

        verify(barberServiceJdbcRepository, times(1)).getAllBarberServices();
        assertEquals(2, result.size());
        assertEquals("service1", result.get(0).getServiceId());
        assertEquals("service2", result.get(1).getServiceId());
    }

    @Test
    public void testAddBarberService() {
        BarberService barberService = new BarberService();
        barberService.setServiceId("service1");

        when(barberServiceJdbcRepository.addBarberService(barberService)).thenReturn(barberService);

        BarberService result = barberServiceServices.addBarberService(barberService);

        verify(barberServiceJdbcRepository, times(1)).addBarberService(barberService);
        assertEquals("service1", result.getServiceId());
    }

    @Test
    public void testGetBarberServiceByServiceId() {
        BarberService barberService = new BarberService();
        barberService.setServiceId("service1");

        when(barberServiceJdbcRepository.getBarberServiceByServiceId("service1")).thenReturn(barberService);

        BarberService result = barberServiceServices.getBarberServiceByServiceId("service1");

        verify(barberServiceJdbcRepository, times(1)).getBarberServiceByServiceId("service1");
        assertEquals("service1", result.getServiceId());
    }

}