package dev.service.cloud.domain.card;

import dev.service.cloud.global.DBUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardRepository {

    public List<String> findLibraryNameByStudentId(Long studentId) {
        String selectQuery = "SELECT name FROM Library WHERE library_id IN (" +
                "SELECT library_id FROM LoanCard WHERE student_id = ?)";

        List<String> libraries = new ArrayList<>();

        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ) {
            preparedStatement.setLong(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    libraries.add(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query error", e);
        }

        return libraries;
    }

    public List<LibraryDAO> findLibrariesWithoutStudent(Long studentId) {
        String selectQuery = "SELECT name, library_id FROM Library WHERE library_id NOT IN (" +
                "SELECT library_id FROM LoanCard WHERE student_id = ?)";

        List<LibraryDAO> libraries = new ArrayList<>();

        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ) {
            preparedStatement.setLong(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    libraries.add(LibraryDAO.of(resultSet.getLong("library_id"), resultSet.getString("name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query error", e);
        }

        return libraries;
    }

    public void save(Long studentId, Long libraryId) {
        String insertQuery = "INSERT INTO `LoanCard` (`student_id`, `library_id`) VALUES (?, ?)";

        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {
            preparedStatement.setLong(1, studentId);
            preparedStatement.setLong(2, libraryId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("LoanCard successfully saved.");
            } else {
                System.out.println("No rows affected, failed to save LoanCard.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database query error", e);
        }
    }

}
