package by.tc.ts.test.unit;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;
import by.tc.ts.test.unit.dataProvider.MyDataProvider;

public class AuthirizationTest extends Assert {


	@Test(dataProvider = "authorizationPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String login, String password) throws DAOException, SAXException, IOException {

			boolean actual = DAOFactory.getInstance().getUserDAO().logination(login, password);
			assertEquals(actual, true, "We expected true result");

	}

	
	@Test(dataProvider = "authorizationNegativeTest", dataProviderClass = MyDataProvider.class)
	public void negativTestOne(String login, String password) throws DAOException {

		boolean actual = DAOFactory.getInstance().getUserDAO().logination(login, password);

		assertFalse(actual, "We expected false");

	}

	
}
