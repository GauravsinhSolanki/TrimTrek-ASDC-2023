package com.ProjectTrial1.Projectdemo1.barberinventory;
import com.ProjectTrial1.Projectdemo1.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BarberInventoryJDBCRepository implements BarberInventoryRepository{
    @Override
    public BarberInventory save(BarberInventory barberInventory) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO barber_inventory (barber_id, product_id, product_name, quantity, description) " +
                             "VALUES (?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, barberInventory.getBarberId());
            statement.setString(2, barberInventory.getProductId());
            statement.setString(3, barberInventory.getProductName());
            statement.setInt(4, barberInventory.getQuantity());
            statement.setString(5, barberInventory.getDescription());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to insert data into barber_inventory table.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    barberInventory.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to get the ID after insert.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return barberInventory;
    }

    @Override
    public List<BarberInventory> findAll() {
        List<BarberInventory> barberInventories = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM barber_inventory");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                BarberInventory barberInventory = mapResultSetToBarberInventory(resultSet);
                barberInventories.add(barberInventory);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return barberInventories;
    }

    @Override
    public BarberInventory findByEmailId(String id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM barber_inventory WHERE barber_id = ?")) {

            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToBarberInventory(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public BarberInventory updateQuantity(String id, int newQuantity) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE barber_inventory SET quantity = ? WHERE id = ?")) {

            statement.setInt(1, newQuantity);
            statement.setString(2, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to update quantity for product with ID: " + id);
            }

            BarberInventory updatedInventory = findByEmailId(id);
            if (updatedInventory != null) {
                return updatedInventory;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, throw custom exception, etc.)
        }

        return null;
    }

    private BarberInventory mapResultSetToBarberInventory(ResultSet resultSet) throws SQLException {
        BarberInventory barberInventory = new BarberInventory();
        barberInventory.setId(resultSet.getInt("id"));
        barberInventory.setBarberId(resultSet.getString("barber_id"));
        barberInventory.setProductId(resultSet.getString("product_id"));
        barberInventory.setProductName(resultSet.getString("product_name"));
        barberInventory.setQuantity(resultSet.getInt("quantity"));
        barberInventory.setDescription(resultSet.getString("description"));
        return barberInventory;
    }
}
