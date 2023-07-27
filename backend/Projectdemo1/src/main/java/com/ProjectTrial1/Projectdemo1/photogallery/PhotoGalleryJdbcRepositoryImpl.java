package com.ProjectTrial1.Projectdemo1.photogallery;

import com.ProjectTrial1.Projectdemo1.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PhotoGalleryJdbcRepositoryImpl implements PhotoGalleryRepository{
    @Override
    public List<PhotoGallery> findAll() {
        List<PhotoGallery> photoGalleryList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionManager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM photo_gallery");

            while (resultSet.next()) {
                PhotoGallery photoGallery = new PhotoGallery();
                photoGallery.setId(resultSet.getInt("id"));
                photoGallery.setImageId(resultSet.getString("image_id"));
                photoGallery.setImageName(resultSet.getString("image_name"));
                photoGallery.setImageDescription(resultSet.getString("image_description"));
                photoGalleryList.add(photoGallery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return photoGalleryList;
    }

    @Override
    public Optional<PhotoGallery> findByImageId(String imageId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PhotoGallery photoGallery = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM photo_gallery WHERE image_id = ?");
            preparedStatement.setString(1, imageId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                photoGallery = new PhotoGallery();
                photoGallery.setId(resultSet.getInt("id"));
                photoGallery.setImageId(resultSet.getString("image_id"));
                photoGallery.setImageName(resultSet.getString("image_name"));
                photoGallery.setImageDescription(resultSet.getString("image_description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }

        return Optional.ofNullable(photoGallery);
    }

    @Override
    public PhotoGallery save(PhotoGallery photoGallery) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO photo_gallery (image_id, image_name, image_description) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, photoGallery.getImageId());
            preparedStatement.setString(2, photoGallery.getImageName());
            preparedStatement.setString(3, photoGallery.getImageDescription());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating PhotoGallery failed, no rows affected.");
            }

            generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                photoGallery.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating PhotoGallery failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, preparedStatement, generatedKeys);
        }

        return photoGallery;
    }

    private void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
