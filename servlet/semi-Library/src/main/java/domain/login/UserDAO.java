package domain.login;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import global.DBUtil;

public class UserDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public long selectUser(String name, Date birth, String address) throws IOException, SQLException {
        User user = null;
        long studentId = -1;
        final String studentSelectQuery = "SELECT student_id FROM STUDENT WHERE name = ? AND birth = ? AND address = ?";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(studentSelectQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, birth);
            preparedStatement.setString(3, address);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                studentId = resultSet.getLong("student_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return studentId;
    }

    public void saveUser(String name, Date birth, String address) {
        final String studentSave = "INSERT INTO `Student` (`name`, `birth`, `address`) VALUES\n" + "(?, ?, ?)";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(studentSave);
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, birth);
            preparedStatement.setString(3, address);
            preparedStatement.executeUpdate();
            System.out.println("도서증이 생성되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}