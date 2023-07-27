package com.ProjectTrial1.Projectdemo1.hirebarber.barberservice;

import com.ProjectTrial1.Projectdemo1.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BarberServiceJdbcRepositoryImpl implements BarberServiceJdbcRepository{
    @Override
    public List<BarberService> getAllBarberServices() {
        List<BarberService> barberServices = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM barber_service");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                BarberService barberService = mapResultSetToBarberService(resultSet);
                barberServices.add(barberService);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barberServices;
    }

    @Override
    public BarberService addBarberService(BarberService barberService) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO barber_service (service_id, service_name, service_details, service_duration, service_charge, additional_charges, service_notes, created_by, created_on, updated_by, updated_on, is_active) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, barberService.getServiceId());
            statement.setString(2, barberService.getServiceName());
            statement.setString(3, barberService.getServiceDetails());
            statement.setInt(4, barberService.getServiceDuration());
            statement.setInt(5, barberService.getServiceCharge());
            statement.setInt(6, barberService.getAdditionalCharges());
            statement.setString(7, barberService.getServiceNotes());
            statement.setString(8, barberService.getCreatedBy());
            statement.setObject(9, barberService.getCreatedOn());
            statement.setString(10, barberService.getUpdatedBy());
            statement.setObject(11, barberService.getUpdatedOn());
            statement.setBoolean(12, barberService.isActive());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding barber service failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    barberService.setId(generatedId);
                } else {
                    throw new SQLException("Adding barber service failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return barberService;
    }

    @Override
    public BarberService getBarberServiceByServiceId(String serviceId) {
        BarberService barberService = null;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM barber_service WHERE service_id = ?")) {

            statement.setString(1, serviceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    barberService = mapResultSetToBarberService(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barberService;
    }


    @Override
    public BarberService findById(int id) {
        BarberService barberService = null;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM barber_service WHERE ID = ?")) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    barberService = mapResultSetToBarberService(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return barberService;
    }
    private BarberService mapResultSetToBarberService(ResultSet resultSet) throws SQLException {
        BarberService barberService = new BarberService();
        barberService.setId(resultSet.getInt("ID"));
        barberService.setServiceId(resultSet.getString("service_id"));
        barberService.setServiceName(resultSet.getString("service_name"));
        barberService.setServiceDetails(resultSet.getString("service_details"));
        barberService.setServiceDuration(resultSet.getInt("service_duration"));
        barberService.setServiceCharge(resultSet.getInt("service_charge"));
        barberService.setAdditionalCharges(resultSet.getInt("additional_charges"));
        barberService.setServiceNotes(resultSet.getString("service_notes"));
        barberService.setCreatedBy(resultSet.getString("created_by"));
        barberService.setCreatedOn(resultSet.getObject("created_on", LocalDateTime.class));
        barberService.setUpdatedBy(resultSet.getString("updated_by"));
        barberService.setUpdatedOn(resultSet.getObject("updated_on", LocalDateTime.class));
        barberService.setActive(resultSet.getBoolean("is_active"));
        return barberService;
    }
}
