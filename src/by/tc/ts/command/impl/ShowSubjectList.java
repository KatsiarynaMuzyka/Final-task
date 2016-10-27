package by.tc.ts.command.impl;

import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;
import by.tc.ts.service.ServiceFactory;
import by.tc.ts.service.exception.ServiceException;

public class ShowSubjectList implements Command{

	@Override
	public Response execute(Request request) throws CommandException {


		Response response = new Response();
		try {
			if (ServiceFactory.getInstance().getTestingSystemService().showSubjectList()) {
				response.setErrorStatus(false);
				response.setResultMessage("Завершено успешно");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Предметов не найдено");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("Предметов не найдено");
			e.printStackTrace();
			return response;
		}
	
		return response;

	}


}
