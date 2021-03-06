package by.tc.ts.command.impl;

import java.util.List;

import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.bean.ShowQuestionListRequest;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;
import by.tc.ts.service.ServiceFactory;
import by.tc.ts.service.exception.ServiceException;

public class ShowQuestionList implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		ShowQuestionListRequest req;

		if (request instanceof ShowQuestionListRequest) {
			req = (ShowQuestionListRequest) request;
		} else {
			throw new CommandException("�������� ������");
		}

		List<String> showQuestList;
		Response response = new Response();

		String subjName = req.getSubjName();

		try {

			showQuestList = ServiceFactory.getInstance().getTestingSystemService().showQuestionList(subjName);
			if (showQuestList != null) {

				response.setShowQuestList(showQuestList);
				response.setErrorStatus(false);
				response.setResultMessage("��������� �������");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("�������� �� �������");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

		return response;
	}

}
