package domain.Return;


import com.mysql.cj.jdbc.exceptions.ConnectionFeatureNotAvailableException;
import global.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;

public class ReturnDataAccessor {

	
	
	
    public ArrayList<ReturnBookDto> showLoanBook(long studentId, long libraryId) {

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ReturnBookDto dto = null;
        ArrayList<ReturnBookDto> arrayList = new ArrayList<>();
        String bookTitle = null;
        Date loanDate;

        try {
            connection = DBUtil.getConnection();
            String showLoanBookSql = "select book_title, loan_date"
                                        + " from Loan"
                                        + " where student_id = ? and library_id = ?";
            pstmt = connection.prepareStatement(showLoanBookSql);
            pstmt.setLong(1,studentId);
            pstmt.setLong(2,libraryId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
//                dto = new ReturnBookDto();
                bookTitle = (rs.getString("book_title"));
                loanDate = (rs.getDate("loan_date"));
                arrayList.add(new ReturnBookDto(bookTitle,loanDate));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
            }
        }
        return arrayList;
    }


    public ReturnBookDto checkLoanDateAndReturn(long studentId, String returnBookTitle, long libraryId) {

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ReturnBookDto dto = null;
        LocalDate loanDate = null;
        int dueDate;
        LocalDate now = LocalDate.now();
        boolean check = false;
        long lateDay = 0;

        try {
            connection = DBUtil.getConnection();
            String checkLoanDateSql = "select a.loan_date, b.due_date"
                                + " from loan a join library b on a.library_id = b.library_id"
                                + " where a.student_id = ? and a.library_id = ? and a.book_title= ?";


            pstmt = connection.prepareStatement(checkLoanDateSql);
            pstmt.setLong(1,studentId);
            pstmt.setLong(2,libraryId);
            pstmt.setString(3,returnBookTitle);

            rs = pstmt.executeQuery();
            if (rs.next()) {

                Date sqlLoanDate = rs.getDate("loan_date");
                if (sqlLoanDate != null) {
                    loanDate = sqlLoanDate.toLocalDate();
                }
                dueDate = (rs.getInt("due_date"));

                if (loanDate != null) {
                    LocalDate dueDateAfterLoan = loanDate.plusDays(dueDate);
                    dto = new ReturnBookDto();

                    if(now.isAfter(dueDateAfterLoan)) { //반납날짜지남
                        lateDay = ChronoUnit.DAYS.between(dueDateAfterLoan, now);
                        dto.setLateCheck(true);
                        dto.setLateDay(lateDay);
                        dto.setLoanDate(sqlLoanDate);
                    }
                    else {
                        dto.setLateCheck(false);
                        dto.setLateDay(0);
                        dto.setLoanDate(sqlLoanDate);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dto;

    }

    public void deleteLoanAndUpdateStock(long studentId, String returnBookTitle, long libraryId) {

        Connection connection = null;
        PreparedStatement pstmt = null;
        ReturnBookDto dto = null;
        ResultSet rs = null;
        int flag = 0;
        int count = 0;

        try {
            connection = DBUtil.getConnection();

            String deleteLoanSql = "delete from loan"
                    + " where student_id = ? and library_id = ? and book_title = ?";

            pstmt = connection.prepareStatement(deleteLoanSql);
            pstmt.setLong(1,studentId);
            pstmt.setLong(2,libraryId);
            pstmt.setString(3,returnBookTitle);

            flag = pstmt.executeUpdate();


            String selectStockSql = "select count"
                    + " from Stock"
                    + " where library_id = ? and book_title = ?";

            pstmt = connection.prepareStatement(selectStockSql);
            pstmt.setLong(1,libraryId);
            pstmt.setString(2,returnBookTitle);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("count");
            }


            String updateStockSql = "update Stock"
                    + " set count = ?"
                    + " where library_id = ? and book_title = ?";

            pstmt = connection.prepareStatement(updateStockSql);
            pstmt.setInt(1,count+1);
            pstmt.setLong(2,libraryId);
            pstmt.setString(3,returnBookTitle);

            flag = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ;

    }



}
