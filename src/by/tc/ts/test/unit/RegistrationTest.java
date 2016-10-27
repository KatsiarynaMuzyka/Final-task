package by.tc.ts.test.unit;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.tc.ts.bean.RegistrationRequest;
import by.tc.ts.bean.Response;
import by.tc.ts.controller.Controller;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;
import by.tc.ts.test.unit.dataProvider.MyDataProvider;


public class RegistrationTest extends Assert{

	
	@Test(dataProvider = "registrationPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String login, String password, String commandName) throws DAOException{
		
		//First you need to make sure that this user does not exist
		//So call deleteUser method
		DAOFactory.getInstance().getUserDAO().deleteUser(login, password);
		
		Controller controller = new Controller();
		RegistrationRequest request = new RegistrationRequest();
		request.setCommandName(commandName);
		request.setLogin(login);
		request.setPassword(password);
		Response response = controller.doRequest(request);
		
		assertEquals(response.isErrorStatus(), false, "Here must be false");
		
	}
	
}
