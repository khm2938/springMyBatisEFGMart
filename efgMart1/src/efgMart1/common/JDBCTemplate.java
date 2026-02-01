package efgMart1.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	// DAO 에서 관리했던 Connection을 JDBCTemplate에서 관리
	public static Connection getConnection() {
		Connection con = null;
		Properties prop = new Properties();

		try {
			
			prop.load(new FileInputStream("resources/driver.properties"));
			
			String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("username");
            String password = prop.getProperty("password");
            
			Class.forName(driver);

			// 2. 신분증 제시
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return con;
	}

	// DAO 에서 관리했던 commit을 JDBCTemplate에서 관리
	public static void commit(Connection con) {
		// con.isClosed() => con 연결이 끊어져있으면 isClose() == true, 연결되어있으면 false
		try {
			if (con != null && !con.isClosed()) {
				con.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// DAO 에서 관리했던 rollback을 JDBCTemplate에서 관리
	public static void rollback(Connection con) {
		// con.isClosed() => con 연결이 끊어져있으면 isClose() == true, 연결되어있으면 false
		try {
			if (con != null && !con.isClosed()) {
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DAO 에서 관리했던 ResultSet, Statement, Connection, PrepareStatement
	// 자원반납기능을 JDBCTemplate에서 관리한다
	/**
	 * Connection 객체를 close 처리해주는 공통함수
	 * @param con
	 */
	public static void close(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Statement 객체를 close 처리해주는 공통함수
	 * PreparedStatement는 Statement의 자식으로 Statement로 부터 
	 * 상속받기때문에 따로 똑같은 메서드를 작성할 필요가 없다.
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ResultSet 객체를 close 처리해주는 공통함수
	 * @param ResultSet 객체
	 */
	public static void close(ResultSet rset) {
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
