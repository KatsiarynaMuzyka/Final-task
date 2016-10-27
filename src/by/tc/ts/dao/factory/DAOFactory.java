package by.tc.ts.dao.factory;

import by.tc.ts.dao.TestingSystemDAO;
import by.tc.ts.dao.UserDAO;
import by.tc.ts.dao.impl.TestingSystemDAOImpl;
import by.tc.ts.dao.impl.UserDAOImpl;

public class DAOFactory {
	private static final DAOFactory INSTANCE = new DAOFactory();

	private final UserDAO userDAO = new UserDAOImpl();
	private final TestingSystemDAO testingSystem = new TestingSystemDAOImpl();

	private DAOFactory() {

	}

	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public TestingSystemDAO getTestingSystem() {
		return testingSystem;
	}
}
