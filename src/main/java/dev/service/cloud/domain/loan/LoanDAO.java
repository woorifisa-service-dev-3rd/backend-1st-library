package dev.service.cloud.domain.loan;

import dev.service.cloud.global.DBUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDate;

@Slf4j
public class LoanDAO {

    public String findLibraryList() {
        final String query = "SELECT lib.name FROM Library lib";
        StringBuilder result = new StringBuilder();

        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            int count = 1;
            while (resultSet.next()) {
                result.append("[").append(count++).append("] ")
                        .append(resultSet.getString(1))
                        .append(" ");
            }

            return result.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public String findBookListByLibraryName(long libraryId) {
        final String query = "SELECT s.book_title, s.count FROM Stock s " +
                "WHERE s.library_id = ( " +
                "    SELECT lib.library_id FROM Library lib WHERE lib.library_id = ?" +
                ");";
        StringBuilder result = new StringBuilder();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setLong(1, libraryId);
            try (ResultSet resultSet = psmt.executeQuery()) {

                result.append("\n현재 도서 목록:\n")
                        .append("|      책 이름       | 재고 |\n")
                        .append("--------------------------\n");

                while (resultSet.next()) {
                    result.append("| ")
                            .append(String.format("%-18s", resultSet.getString("book_title")))
                            .append(" | ")
                            .append(resultSet.getInt("count"))
                            .append(" |\n");
                }

                return result.toString();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public long findMaxLoan(long libraryId) {
        final String query = "SELECT lib.max_loan FROM Library lib\n" +
                "WHERE lib.library_id = ?;";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setLong(1, libraryId);
            try (ResultSet resultSet = psmt.executeQuery()) {
                resultSet.next();
                return resultSet.getInt("max_loan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String findLibraryById(long libraryId) {
        final String query = "SELECT lib.name FROM Library lib WHERE lib.library_id = ?;";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setLong(1, libraryId);
            try (ResultSet resultSet = psmt.executeQuery()) {
                resultSet.next();
                return resultSet.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public boolean checkLoanCardExist(long libraryId, long memberId) {
        final String query = "SELECT lc.load_card_id FROM LoanCard lc\n" +
                "WHERE lc.student_id = ? AND lc.library_id = ?;";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setLong(1, memberId);
            psmt.setLong(2, libraryId);

            try (ResultSet resultSet = psmt.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int findDutDateByLibraryId(long libraryId) {
        final String query = "SELECT lib.due_date FROM Library lib\n" +
                "WHERE lib.library_id = ?;";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setLong(1, libraryId);

            try (ResultSet resultSet = psmt.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean checkStockAvailability(String bookTitle, long libraryId) {
        final String query = "SELECT count FROM Stock WHERE book_title = ? AND library_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setString(1, bookTitle);
            psmt.setLong(2, libraryId);

            try (ResultSet resultSet = psmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("count") > 0;
                }
            }
        } catch (SQLException e) {
            log.error("Error checking stock availability", e);
        }
        return false;
    }

    public void createLoan(String bookTitle, long student_id, long library_id) {
        final String query = "INSERT INTO Loan (loan_date, book_title, student_id, library_id, count) " +
                "VALUES (?, ?, ?, ?, 1)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setDate(1, Date.valueOf(LocalDate.now()));
            psmt.setString(2, bookTitle);
            psmt.setLong(3, student_id);
            psmt.setLong(4, library_id);

            psmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error creating loan", e);
        }
    }

    public void decreaseStock(String bookTitle, long library_id) {
        final String query = "UPDATE Stock SET count = count - 1 " +
                "WHERE book_title = ? AND library_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setString(1, bookTitle);
            psmt.setLong(2, library_id);

            psmt.executeUpdate();
        } catch (SQLException e) {
            log.error("Error decreasing stock", e);
        }
    }
}
