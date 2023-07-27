package com.ProjectTrial1.Projectdemo1.hirebarber.slotbooking;

import com.ProjectTrial1.Projectdemo1.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBookedSlotsRepository implements BookedSlotsRepository{
    @Override
    public List<BookedSlots> findByServiceDateOrderByStartTimeAsc(LocalDate serviceDate) {
        List<BookedSlots> slotsList = new ArrayList<>();
        String sql = "SELECT * FROM booked_slots WHERE service_date = ? ORDER BY start_time ASC";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, serviceDate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    slotsList.add(mapResultSetToBookedSlots(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slotsList;
    }

    @Override
    public List<BookedSlots> findByCustomerIdOrderByServiceDateAsc(String customerId) {
        List<BookedSlots> slotsList = new ArrayList<>();
        String sql = "SELECT * FROM booked_slots WHERE customer_id = ? ORDER BY service_date ASC";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    slotsList.add(mapResultSetToBookedSlots(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slotsList;
    }

    @Override
    public List<BookedSlots> findByBarberIdAndServiceDate(String barberId, Optional<LocalDate> serviceDate) {
        List<BookedSlots> slotsList = new ArrayList<>();
        String sql = "SELECT * FROM booked_slots WHERE barber_id = ? AND service_date = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, barberId);
            statement.setObject(2, serviceDate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    slotsList.add(mapResultSetToBookedSlots(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slotsList;
    }

    public BookedSlots findByBookingId(String bookingId) {
        String query = "SELECT * FROM booked_slots WHERE booking_id = ?";
        BookedSlots bookedSlots = null;

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bookingId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                bookedSlots = mapResultSetToBookedSlots(resultSet);

                // Set other properties as needed
            }

            resultSet.close();
        } catch (SQLException e) {
            // Handle the exception if needed
            e.printStackTrace();
        }

        return bookedSlots;
    }

    public List<BookedSlots> findByServiceDate(LocalDate serviceDate) {
        String query = "SELECT * FROM booked_slots WHERE service_date = ?";
        List<BookedSlots> bookedSlotsList = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(serviceDate));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BookedSlots bookedSlots = mapResultSetToBookedSlots(resultSet);
                // Set other properties as needed
                bookedSlotsList.add(bookedSlots);
            }

            resultSet.close();
        } catch (SQLException e) {
            // Handle the exception if needed
            e.printStackTrace();
        }

        return bookedSlotsList;
    }

    public List<BookedSlots> findByCustomerIdAndServiceDate(String customerId, LocalDate serviceDate) {
        String query = "SELECT * FROM booked_slots WHERE customer_id = ? AND service_date = ?";
        List<BookedSlots> bookedSlotsList = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customerId);
            statement.setDate(2, java.sql.Date.valueOf(serviceDate));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BookedSlots bookedSlots = mapResultSetToBookedSlots(resultSet);

                // Set other properties as needed
                bookedSlotsList.add(bookedSlots);
            }

            resultSet.close();
        } catch (SQLException e) {
            // Handle the exception if needed
            e.printStackTrace();
        }

        return bookedSlotsList;
    }
    @Override
    public BookedSlots save(BookedSlots bookedSlots) {
        try (Connection connection = ConnectionManager.getConnection();


        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO booked_slots (booking_id, customer_id, barber_Id, service_id, service_date, service_duration, start_time, end_time, is_active) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, bookedSlots.getBookingId());
            statement.setString(2, bookedSlots.getCustomerId());
            statement.setString(3, bookedSlots.getBarberId());
            statement.setInt(4, bookedSlots.getServiceId());
            statement.setDate(5, java.sql.Date.valueOf(bookedSlots.getServiceDate()));
            statement.setInt(6, bookedSlots.getServiceDuration());
            statement.setTime(7, java.sql.Time.valueOf(bookedSlots.getStartTime()));
            statement.setTime(8, java.sql.Time.valueOf(bookedSlots.getEndTime()));
            statement.setBoolean(9, bookedSlots.isActive());
//            statement.setString(10, bookedSlots.setServiceZoneId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    bookedSlots.setId(generatedId);
                } else {
                    throw new SQLException("Adding failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookedSlots;
    }

    @Override
    public List<BookedSlots> findAll() {
        List<BookedSlots> bookedSlotsArrayList = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM booked_slots");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                BookedSlots bookedSlots = mapResultSetToBookedSlots(resultSet);
                bookedSlotsArrayList.add(bookedSlots);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return bookedSlotsArrayList;
    }

    private BookedSlots mapResultSetToBookedSlots(ResultSet resultSet) throws SQLException {
        BookedSlots bookedSlots = new BookedSlots();
        bookedSlots.setBookingId(resultSet.getString("booking_id"));
        bookedSlots.setStartTime(resultSet.getTime("start_time").toLocalTime());
               bookedSlots.setEndTime(resultSet.getTime("end_time").toLocalTime());
               bookedSlots.setCustomerId(resultSet.getString("customer_id"));
               bookedSlots.setBarberId(resultSet.getString("barber_id"));
               bookedSlots.setServiceDate(resultSet.getDate("service_date").toLocalDate());
               bookedSlots.setServiceId(resultSet.getInt("service_id"));
               bookedSlots.setServiceDuration(resultSet.getInt("service_duration"));

        return bookedSlots;
    }

}
