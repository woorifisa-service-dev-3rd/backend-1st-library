package dev.service.cloud.domain.student;

import dev.service.cloud.global.DBUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Student> selectAll() {
        final String studentSelectQuery = "SELECT * FROM STUDENT";
        List<Student> students = new ArrayList<>();

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(studentSelectQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long id = resultSet.getLong("student_id");
                String name = resultSet.getString("name");
                Date date = Date.valueOf(resultSet.getString("birth"));
                String address = resultSet.getString("address");

                students.add(new Student(id, name, date, address));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return students;
    }

    public long loginStudent(String name, Date birth, String address) throws IOException, SQLException {
        Student student = null;
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

    public void saveStudent(String name, Date birth, String address) {
        final String studentSelectQuery = "INSERT INTO `Student` (`name`, `birth`, `address`) VALUES\n" +
                "                                                       (?, ?, ?)";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(studentSelectQuery);
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
