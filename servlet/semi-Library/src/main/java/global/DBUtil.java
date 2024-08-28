package global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
	public static Connection getConnection() {
        Connection connection = null;
        try {

            String dbUrl = "jdbc:mysql://localhost:3306/test";
            String userName = "root";
            String password = "1234";
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            // DB 커넥션 생성
            connection = DriverManager.getConnection(dbUrl, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return connection;
    }
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
				rset = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection con, PreparedStatement pstmt) {
		try {

			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}