package by.tc.ts.dao;

import by.tc.ts.dao.exception.DAOException;

public interface UserDAO {

	public boolean logination(String login, String password) throws DAOException;
	
	public boolean registration(String login, String password) throws DAOException;
	
	public void deleteUser(String login, String password) throws DAOException;
	
}
