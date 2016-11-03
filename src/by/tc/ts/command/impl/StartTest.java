package by.tc.ts.command.impl;

import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.bean.StartTestRequest;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;
import by.tc.ts.service.ServiceFactory;
import by.tc.ts.service.exception.ServiceException;

public class StartTest implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		StartTestRequest req;

		if (request instanceof StartTestRequest) {
			req = (StartTestRequest) request;
		} else {
			throw new CommandException("Неверный запрос");
		}

		Object test[][] = new Object[1][2];
		Response response = new Response();
		String subjName = req.getSubjName();

		try {

			test = ServiceFactory.getInstance().getTestingSystemService().startTest(subjName);
			if (test != null) {

				response.setTest(test);
				response.setErrorStatus(false);
				response.setErrorMessage("Тест завершен");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Невозможно завершить тест");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;

	}

}
