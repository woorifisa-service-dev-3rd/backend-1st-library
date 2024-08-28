package domain.card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import global.DBUtil;

public class LibraryDAO {
    public Long findByName(String name) {
        String selectQuery = "SELECT library_id FROM Library WHERE name = ?";

        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query error", e);
        }

        return -1L;
    }
}
