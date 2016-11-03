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

		login = login.trim();
		password = password.trim();
		
		if ((login == null || login.length() < 1) || (password == null || password.length() < 1)) {
			throw new DAOException("¬ведите корректные логин и пароль");
		}
		
		Connection con = null;
		Statement st = null;

		try {

			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();
			int result = st
					.executeUpdate("INSERT INTO users (login, password) VALUES ('" + login + "', '" + password + "');");
			if (result != 0) {
				return true;
			} else {
				return false;
			}
			
			
			
		} catch (SQLException | InterruptedException e) {
			throw new DAOException(e.getMessage());
		}

		finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DAOException(e.getMessage());
				}
			}
			try {
				SQLConnection.getInstance().returnConnection(con);
			} catch (SQLException | InterruptedException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	@Override
	public boolean logination(String login, String password) throws DAOException {

		login = login.trim();
		password = password.trim();
		
		if ((login == null || login.length() < 1) || (password == null || password.length() < 1)) {
			throw new DAOException("¬ведите корректные логин и пароль");
		}
		
		Connection con = null;
		Statement st = null;

		try {
			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();

			ResultSet result = st
					.executeQuery("SELECT id FROM users WHERE(login, password)=('" + login + "','" + password + "');");
			if (result.next() == false) {
				return false;
			} else {
				int userKey = result.getInt("id");
				UserKey.setUserKey(userKey);
				st.close();
				return true;
			}

		} catch (SQLException | InterruptedException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DAOException(e.getMessage());
				}
			}
			try {
				SQLConnection.getInstance().returnConnection(con);
			} catch (SQLException | InterruptedException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	public void deleteUser(String login, String password) throws DAOException {

		Connection con = null;
		Statement st = null;

		try {
			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();
			int result = st
					.executeUpdate("DELETE FROM users Where(login, password)=('" + login + "','" + password + "');");
		} catch (SQLException | InterruptedException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DAOException(e.getMessage());
				}
			}
			try {
				SQLConnection.getInstance().returnConnection(con);
			} catch (SQLException | InterruptedException e) {
				throw new DAOException(e.getMessage());
			}
		}

	}

}
