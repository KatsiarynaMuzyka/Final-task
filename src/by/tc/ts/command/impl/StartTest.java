package by.tc.ts.command.impl;

import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.bean.StartTestRequest;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;
import by.tc.ts.service.ServiceFactory;
import by.tc.ts.service.exception.ServiceException;

public class StartTest implements Command{

	@Override
	public Response execute(Request request) throws CommandException {
		
		StartTestRequest req;

		if (request instanceof StartTestRequest) {
			req = (StartTestRequest) request;
		} else {
			throw new CommandException("Неверный запрос");
		}

		Response response = new Response();
		String subjName = req.getSubjName();

		try {
			if (ServiceFactory.getInstance().getTestingSystemService().startTest(subjName)) {
				response.setErrorStatus(false);
				response.setErrorMessage("Тест завершен");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Ошибка");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Ошибка");
			e.printStackTrace();
			return response;
		}
		return response;
		
	}

}
