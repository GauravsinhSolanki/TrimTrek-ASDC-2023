package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "barber_id", "start_time", "day_of_week" }) })
@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BarberShift {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	@NonNull
	@Column(name = "barber_id")
	private String barberId;
	@NonNull
	@Column(name = "start_time")

	private LocalTime startTime;
	@NonNull
	@Column(name = "end_time")
	private LocalTime endTime;
	@NonNull
	@Column(name = "day_of_week")
	private String dayOfWeek;

//	@Column(name = "service_zone_id")

//	@OneToOne
//	private ServiceZone serviceZoneId;

	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;



}
