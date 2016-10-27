package by.tc.ts.command.impl;

import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.bean.ShowQuestionListRequest;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;
import by.tc.ts.service.ServiceFactory;
import by.tc.ts.service.exception.ServiceException;

public class ShowQuestionList implements Command{

	@Override
	public Response execute(Request request) throws CommandException {

		ShowQuestionListRequest req;

		if (request instanceof ShowQuestionListRequest) {
			req = (ShowQuestionListRequest) request;
		} else {
			throw new CommandException("Неверный запрос");
		}

		Response response = new Response();

		String subjName = req.getSubjName();

		try {
			if (ServiceFactory.getInstance().getTestingSystemService().showQuestionList(subjName)) {
				response.setErrorStatus(false);
				response.setResultMessage("Завершено успешно");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Вопросов не найдено");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Вопросов не найдено");
			e.printStackTrace();
		}

		return response;
	}
	
	

}
