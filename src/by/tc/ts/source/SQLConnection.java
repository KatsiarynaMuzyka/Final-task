package by.tc.ts.source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SQLConnection {

	private static final SQLConnection instance = new SQLConnection();

	private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(5);

	private SQLConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			for (int index = 0; index < pool.remainingCapacity(); index++) {
				pool.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/TESTINGSYSTEM?useSSL=false", "root",
						"root"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws InterruptedException {
		return pool.take();
	}

	public void returnConnection(Connection connection) throws SQLException, InterruptedException {

		if (connection == null) {
			return;
		}

		connection.setAutoCommit(true);
		connection.setReadOnly(false);

		pool.put(connection);
		return;
	}

	public void closePool() throws SQLException {

		for (Connection con : pool) {
			con.close();
		}
	}

	public static SQLConnection getInstance() {

		return instance;
	}
	
}
