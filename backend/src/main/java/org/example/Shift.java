package org.example;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "shifts")
public class Shift {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shiftId;


    private Date date;

    private String startTime;

    private String endTime;

    public Shift(Date date, String startTime, String endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Shift() {

    }


    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "shiftId=" + shiftId +
                ", date=" + date +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shift shift = (Shift) o;
        return getShiftId().equals(shift.getShiftId()) && Objects.equals(getDate(), shift.getDate()) && Objects.equals(getStartTime(), shift.getStartTime()) && Objects.equals(getEndTime(), shift.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShiftId(), getDate(), getStartTime(), getEndTime());
    }
}
