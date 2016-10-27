package by.tc.ts.test.unit;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.tc.ts.bean.Response;
import by.tc.ts.bean.ShowQuestionListRequest;
import by.tc.ts.controller.Controller;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;
import by.tc.ts.test.unit.dataProvider.MyDataProvider;


public class ShowQuestionListTest extends Assert {

	@Test(dataProvider = "showQuestionPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String subjectName, String commandName) {

		Response response = new Response();
		Controller controller = new Controller();
		ShowQuestionListRequest request = new ShowQuestionListRequest();
		request.setCommandName(commandName);
		request.setSubjName(subjectName);
		response = controller.doRequest(request);

		assertEquals(response.isErrorStatus(), false, "It's may be false");
	}

	@Test(dataProvider = "showQuestionPositiveTestSecond", dataProviderClass = MyDataProvider.class)
	public void positiveTestSecond(String subjName, List<String> expected) throws DAOException {

		List<String> actual = DAOFactory.getInstance().getTestingSystem().showQuestionList(subjName);

		assertEquals(actual, expected, "Here must be no different");
	}

	@Test(expectedExceptions = { Exception.class })
	public void negativeTest() {

		Response response = new Response();
		Controller controller = new Controller();
		ShowQuestionListRequest request = new ShowQuestionListRequest();
		request.setCommandName(null);
		request.setSubjName(null);
		response = controller.doRequest(request);

	}

	/* 
	 * @Test(expectedExceptions = {ServiceException.class}, dataProvider =
	 * "showQuestionNegativeTest", dataProviderClass = MyDataProvider.class)
	 * public void negativeTest(String subjectName, String commandName) { //
	 * DAOFactory.getInstance().getTestApp().clearDB();
	 * 
	 * Response response = new Response(); Controller controller = new
	 * Controller(); ShowTestListRequest request = new ShowTestListRequest();
	 * request.setCommandName(commandName); request.setSubjectId(subjectName);
	 * response = controller.doRequest(request);
	 * 
	 * //assertEquals(response.isErrorStatus(), false, "It's may be false"); }
	 */

}
