package dev.service.cloud.domain.request;

import dev.service.cloud.global.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RequestDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void saveRequest(long library_id, String book_title) {
        final String requestSave = "INSERT INTO `Stock` (`count`, `book_title`, `library_id`) VALUES\n" + "(?, ?, ?)";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(requestSave);
            preparedStatement.setInt(1, 5);
            preparedStatement.setString(2, book_title);
            preparedStatement.setLong(3, library_id);
            preparedStatement.executeUpdate();
            System.out.println("신청되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public long isStock(long library_id, String book_title) {
        final String requestSelectQuery = "SELECT stock_id FROM Stock WHERE library_id = ? AND book_title = ?";
        long stockId = -1;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(requestSelectQuery);
            preparedStatement.setLong(1, library_id);
            preparedStatement.setString(2, book_title);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                stockId = resultSet.getLong("stock_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return stockId;
    }

}
