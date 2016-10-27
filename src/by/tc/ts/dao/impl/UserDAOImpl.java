package by.tc.ts.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.tc.ts.bean.entity.UserKey;
import by.tc.ts.dao.UserDAO;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.source.SQLConnection;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean registration(String login, String password) throws DAOException {

		Connection con = null;
		Statement st = null;

		try {

			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();
			int result = st.executeUpdate(
					"INSERT INTO users (login, password) VALUES ('" + login + "', '" + password + "');");
			if (result != 0) {
				return true;
			} else {
				return false;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			try {
				SQLConnection.getInstance().returnConnection(con);
			} catch (SQLException e) {
			} catch (InterruptedException e) {
			}
		}

		return false;
	}

	@Override
	public boolean logination(String login, String password) throws DAOException {

		Connection con = null;
		Statement st = null;

		try {
			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();

			ResultSet result = st.executeQuery(
					"SELECT id FROM users WHERE(login, password)=('" + login + "','" + password + "');");
			if (result.next() == false) {
				return false;
			} else {
				int userKey = result.getInt("id");
				UserKey.setUserKey(userKey);
				st.close();
				return true;
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			try {
				SQLConnection.getInstance().returnConnection(con);
			} catch (SQLException e) {
			} catch (InterruptedException e) {
			}
		}

		return false;
	}
	
	public void deleteUser(String login, String password) throws DAOException {

		Connection con = null;
		Statement st = null;

		try {
			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();
			int result = st
					.executeUpdate("DELETE FROM users Where(login, password)=('" + login + "','" + password + "');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			try {
				SQLConnection.getInstance().returnConnection(con);
			} catch (SQLException e) {
			} catch (InterruptedException e) {
			}
		}

	}

}
