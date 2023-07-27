package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcHelper {
	private final static String DRIVE = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final static String USERNAME = "sa";
	private final static String PASSWORD = "sa";
	private final static String SERVER = "localhost";
	private final static String PORT = "1433";
	private final static String DATABASE_NAME = "ToTo";
	private final static boolean USING_SSL = true;
	private static StringBuilder CONNECT_STRING = new StringBuilder();
	private static Connection connect = null;
	static {
		try {
			Class.forName(DRIVE);
			CONNECT_STRING.append("jdbc:sqlserver://").append(SERVER).append(":").append(PORT).append(";")
					.append("databasename=").append(DATABASE_NAME).append(";").append("user=").append(USERNAME)
					.append(";").append("password=").append(PASSWORD).append(";")
					.append(USING_SSL ? "encrypt=true;trustServerCertificate=true;" : "");
			System.out.println("Connect String =" + CONNECT_STRING.toString());
			
			;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			connect = DriverManager.getConnection(CONNECT_STRING.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;

	}

	public static Connection getConnection(String databaseName) throws SQLException {
		CONNECT_STRING.setLength(0);
		CONNECT_STRING.append("jdbc:sqlserver://").append(SERVER).append(":").append(PORT).append(";")
				.append("databasename=").append(databaseName).append(";").append("user=").append(USERNAME).append(";")
				.append("password=").append(PASSWORD).append(";")
				.append(USING_SSL ? "encrypt=true;trustServerCertificate=true;" : "");
		return DriverManager.getConnection(CONNECT_STRING.toString());
	}

	public static void main(String[] args) throws SQLException, Exception {
		String version = JdbcHelper.getConnection().getMetaData().getDatabaseProductVersion();
		System.out.println(version.length() > 0 ? "Connected" : "");

	}

	public static PreparedStatement getStmt(String sql, Object... args) throws Exception {
		Connection conn = JdbcHelper.getConnection();
		PreparedStatement stmt;
		if (sql.trim().startsWith("{"))
			stmt = conn.prepareCall(sql);
		else
			stmt = conn.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			try {
				if (args[i].toString().trim().equals("")) {
					args[i] = null;
				}
			} catch (Exception e) {
			}
			stmt.setObject(i + 1, args[i]);
		}
		return stmt;
	}

	public static ResultSet query(String sql, Object... args) throws SQLException, Exception {
		return JdbcHelper.getStmt(sql, args).executeQuery();
	}

	public static Object value(String sql, Object... args) {
		try {
			ResultSet rs = JdbcHelper.query(sql, args);
			if (rs.next()) {
				return rs.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static int update(String sql, Object... args) {
		try {
			PreparedStatement stmt = JdbcHelper.getStmt(sql, args);

			try {
				return stmt.executeUpdate();
			}
			catch (Exception e) {
				e.printStackTrace();
			}finally {
				stmt.getConnection().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
