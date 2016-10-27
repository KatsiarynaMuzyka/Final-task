package by.tc.ts.command.impl;

import by.tc.ts.bean.CreateNewQuestionRequest;
import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;

public class CreateNewQuestion implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		CreateNewQuestionRequest req;

		if (request instanceof CreateNewQuestionRequest) {
			req = (CreateNewQuestionRequest) request;
		} else {
			throw new CommandException("�������� ������");
		}
		Response response = new Response();

		String subjName = req.getSubjName();
		int answer = req.getAnswer();
		String question = req.getQuestion();

		try {
			if (DAOFactory.getInstance().getTestingSystem().createNewQuestion(question, answer, subjName)) {

				response.setErrorStatus(false);
				response.setResultMessage("������ ��������");

			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("������");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			response.setErrorStatus(true);
			response.setErrorMessage("������");
			return response;
		}
		return response;

	}

}
