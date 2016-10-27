package by.tc.ts.test.unit;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.controller.Controller;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;
import by.tc.ts.test.unit.dataProvider.MyDataProvider;


public class ShowSubjectListTest extends Assert {

	@Test(dataProvider = "showSubjectPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String commandName) throws DAOException {

		Response response = new Response();
		Controller controller = new Controller();
		Request request = new Request();
		request.setCommandName(commandName);
		response = controller.doRequest(request);

		assertEquals(response.isErrorStatus(), false, "It's may be false");

	}
	
	@Test(dataProvider = "showSubjectPositiveTestSecond", dataProviderClass = MyDataProvider.class)
	public void secondPositiveTest(List<String> expected) throws DAOException{
		
		List<String> actual = new ArrayList<>();
		actual = DAOFactory.getInstance().getTestingSystem().showSubjList();
		
		assertEquals(actual, expected, "I thought here is no different");
		
	}


}
