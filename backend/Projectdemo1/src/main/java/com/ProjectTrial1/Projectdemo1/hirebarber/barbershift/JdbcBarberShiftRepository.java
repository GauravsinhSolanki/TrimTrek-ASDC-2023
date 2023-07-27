package com.ProjectTrial1.Projectdemo1.hirebarber.barbershift;

import com.ProjectTrial1.Projectdemo1.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcBarberShiftRepository implements BarberShiftRepository{
    private static final String SELECT_ALL_SHIFTS = "SELECT * FROM barber_shift";
    private static final String SELECT_BY_DAY_OF_WEEK = "SELECT * FROM barber_shift WHERE day_of_week=?";
    private static final String SELECT_BY_BARBER_ID = "SELECT * FROM barber_shift WHERE barber_id=?";
    private static final String INSERT_SHIFT = "INSERT INTO barber_shift (barber_id, start_time, end_time, day_of_week, created_by, created_on) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public List<BarberShift> findAll() {
        List<BarberShift> shifts = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SHIFTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                shifts.add(createBarberShiftFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shifts;
    }

    @Override
    public List<BarberShift> findByDayOfWeek(String dayOfWeek) {
        List<BarberShift> shifts = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_DAY_OF_WEEK)) {
            statement.setString(1, dayOfWeek);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                shifts.add(createBarberShiftFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shifts;
    }

    @Override
    public List<BarberShift> findByBarberId(String barberId) {
        List<BarberShift> shifts = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_BARBER_ID)) {
            statement.setString(1, barberId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                shifts.add(createBarberShiftFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shifts;
    }

    @Override
    public BarberShift save(BarberShift barberShift) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SHIFT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, barberShift.getBarberId());
            statement.setTime(2, Time.valueOf(barberShift.getStartTime()));
            statement.setTime(3, Time.valueOf(barberShift.getEndTime()));
            statement.setString(4, barberShift.getDayOfWeek());
            statement.setString(5, barberShift.getCreatedBy());
            statement.setTimestamp(6, Timestamp.valueOf(barberShift.getCreatedOn()));

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating barber shift failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    barberShift.setId(generatedId);
                } else {
                    throw new SQLException("Creating barber shift failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barberShift;
    }

    private BarberShift createBarberShiftFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String barberId = resultSet.getString("barber_id");
        LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
        LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
        String dayOfWeek = resultSet.getString("day_of_week");
        String createdBy = resultSet.getString("created_by");
        LocalDateTime createdOn = resultSet.getTimestamp("created_on").toLocalDateTime();

        return new BarberShift(id, barberId, startTime, endTime, dayOfWeek, createdBy, createdOn, null, null);
    }
}
