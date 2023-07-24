package com.ProjectTrial1.Projectdemo1.hirebarber.servicezone;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceZone {


    @javax.persistence.Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(name = "service_zone_id")
    private String serviceZoneId;
    @Column(name = "service_zone_name")
    private String serviceZoneName;
    @Nullable
    @Column(name = "service_zone_description")
    private String serviceZoneDescription;


}
