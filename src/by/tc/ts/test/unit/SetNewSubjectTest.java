package by.tc.ts.test.unit;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.tc.ts.bean.CreateNewSubjectRequest;
import by.tc.ts.bean.Response;
import by.tc.ts.controller.Controller;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;
import by.tc.ts.test.unit.dataProvider.MyDataProvider;


public class SetNewSubjectTest extends Assert {

	@Test(dataProvider = "setNewSubjectPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String subjectName, String commandName) throws DAOException {

		// delete all subjects for first
		DAOFactory.getInstance().getTestingSystem().deleteAllSubjects();

		Response response = new Response();
		Controller controller = new Controller();
		CreateNewSubjectRequest request = new CreateNewSubjectRequest();
		request.setSubjName(subjectName);
		request.setCommandName(commandName);
		response = controller.doRequest(request);

		assertEquals(response.isErrorStatus(), false, "It's may be false");
	}

	@Test(expectedExceptions = { Exception.class })
	public void negativeTestOne() throws Exception {

		Response response = new Response();
		Controller controller = new Controller();
		CreateNewSubjectRequest request = new CreateNewSubjectRequest();
		request.setSubjName(null);
		request.setCommandName(null);
		response = controller.doRequest(request);

		assertEquals(response.isErrorStatus(), true, "It's may be false");
	}
}
