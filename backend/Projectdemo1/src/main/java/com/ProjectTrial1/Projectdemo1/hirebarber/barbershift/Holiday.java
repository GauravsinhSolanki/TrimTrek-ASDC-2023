package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Holiday {

    @javax.persistence.Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Enumerated(EnumType.STRING)
    private holidayEnum holidayId;
    @NonNull
    private LocalDate festivalDate;
    @NonNull
    private String festivalName;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

}
