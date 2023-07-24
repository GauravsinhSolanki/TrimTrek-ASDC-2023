package com.ProjectTrial1.Projectdemo1.hirebarber.barberservice;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BarberService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int Id;

    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_details")
    private String serviceDetails;

    @Column(name = "service_duration")
    private int serviceDuration;

    @Column(name = "service_charge")
    private int serviceCharge;

    @Column(name = "additional_charges")
    private int additionalCharges;

    @Column(name = "service_notes")
    private String serviceNotes;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "is_active")
    private boolean isActive = true;


}
