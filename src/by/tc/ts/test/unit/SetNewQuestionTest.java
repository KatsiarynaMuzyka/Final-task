package by.tc.ts.test.unit;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.tc.ts.bean.CreateNewQuestionRequest;
import by.tc.ts.bean.Response;
import by.tc.ts.controller.Controller;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;
import by.tc.ts.test.unit.dataProvider.MyDataProvider;

public class SetNewQuestionTest extends Assert {

	@Test(dataProvider = "setNewQuestionPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positivTest(String subjectId, String question, int answer, String CommandName) throws DAOException {

		//delete all question first
		DAOFactory.getInstance().getTestingSystem().deleteAllQuestions();
		
		Response response = new Response();
		Controller controller = new Controller();
		CreateNewQuestionRequest request = new CreateNewQuestionRequest();
		request.setAnswer(answer);
		request.setQuestion(question);
		request.setSubjName(subjectId);
		request.setCommandName(CommandName);
		response = controller.doRequest(request);
 
		assertEquals(response.isErrorStatus(), false, "It's may be false");
	}
	

	@Test(expectedExceptions = {Exception.class})
	public void negativeTestOne(){
		
		Response response = new Response();
		Controller controller = new Controller();
		CreateNewQuestionRequest request = new CreateNewQuestionRequest();
		request.setAnswer(0);
		request.setQuestion("");
		request.setSubjName(null);
		request.setCommandName(null);
		response = controller.doRequest(request);
		
	}

}
