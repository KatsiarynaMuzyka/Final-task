package by.tc.ts.command.impl;

import java.util.ArrayList;
import java.util.List;

import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;
import by.tc.ts.service.ServiceFactory;
import by.tc.ts.service.exception.ServiceException;

public class ShowSubjectList implements Command{

	@Override
	public Response execute(Request request) throws CommandException {

		List<String> showSubjList = new ArrayList<>();
		Response response = new Response();
		try {
			showSubjList = ServiceFactory.getInstance().getTestingSystemService().showSubjectList();
			if (showSubjList!=null) {
				response.setShowSubjList(showSubjList);
				response.setErrorStatus(false);
				response.setResultMessage("Завершено успешно");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Предметов не найдено");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}
	
		return response;

	}


}
