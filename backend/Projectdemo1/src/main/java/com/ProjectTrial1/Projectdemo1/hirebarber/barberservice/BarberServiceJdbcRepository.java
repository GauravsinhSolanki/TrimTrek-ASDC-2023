package com.ProjectTrial1.Projectdemo1.hirebarber.barberservice;


import com.ProjectTrial1.Projectdemo1.account.userrole.UserRole;

import java.util.List;

public interface BarberServiceJdbcRepository {
    List<BarberService> getAllBarberServices();
    BarberService addBarberService(BarberService barberService);
    BarberService getBarberServiceByServiceId(String serviceId);

    BarberService findById(int id);
}
