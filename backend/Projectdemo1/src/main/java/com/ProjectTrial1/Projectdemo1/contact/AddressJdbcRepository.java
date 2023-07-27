package com.ProjectTrial1.Projectdemo1.contact;

import com.ProjectTrial1.Projectdemo1.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressJdbcRepository implements AddressRepository{
    private Connection connection;

    public AddressJdbcRepository() {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public Address addAddress(Address address) {
        String sql = "INSERT INTO address (address_id, user_id, state, city, house, locality, pin_code, created_by, created_on) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, address.getAddressId());
            pstmt.setString(2, address.getUserId());
            pstmt.setString(3, address.getState());
            pstmt.setString(4, address.getCity());
            pstmt.setString(5, address.getHouse());
            pstmt.setString(6, address.getLocality());
            pstmt.setString(7, address.getPinCode());
            pstmt.setString(8, address.getCreatedBy());
            pstmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                address.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception accordingly.
        }
        return address;
    }

    @Override
    public List<Address> getAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        String sql = "SELECT * FROM address";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setAddressId(rs.getString("address_id"));
                address.setUserId(rs.getString("user_id"));
                address.setState(rs.getString("state"));
                address.setCity(rs.getString("city"));
                address.setHouse(rs.getString("house"));
                address.setLocality(rs.getString("locality"));
                address.setPinCode(rs.getString("pin_code"));
                address.setCreatedBy(rs.getString("created_by"));
                address.setCreatedOn(rs.getTimestamp("created_on").toLocalDateTime());

                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception accordingly.
        }
        return addresses;
    }

    @Override
    public List<Address> getAddressByUserId(String userId) {
        List<Address> addresses = new ArrayList<>();
        String sql = "SELECT * FROM address WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setAddressId(rs.getString("address_id"));
                address.setUserId(rs.getString("user_id"));
                address.setState(rs.getString("state"));
                address.setCity(rs.getString("city"));
                address.setHouse(rs.getString("house"));
                address.setLocality(rs.getString("locality"));
                address.setPinCode(rs.getString("pin_code"));
                address.setCreatedBy(rs.getString("created_by"));
                address.setCreatedOn(rs.getTimestamp("created_on").toLocalDateTime());

                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception accordingly.
        }
        return addresses;
    }

    @Override
    public Address getAddressByAddressId(String addressId) {
        Address address = null;
        String sql = "SELECT * FROM address WHERE address_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, addressId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                address = new Address();
                address.setId(rs.getInt("id"));
                address.setAddressId(rs.getString("address_id"));
                address.setUserId(rs.getString("user_id"));
                address.setState(rs.getString("state"));
                address.setCity(rs.getString("city"));
                address.setHouse(rs.getString("house"));
                address.setLocality(rs.getString("locality"));
                address.setPinCode(rs.getString("pin_code"));
                address.setCreatedBy(rs.getString("created_by"));
                address.setCreatedOn(rs.getTimestamp("created_on").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception accordingly.
        }
        return address;
    }
}
