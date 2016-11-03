package by.tc.ts.command.impl;

import by.tc.ts.bean.CreateNewSubjectRequest;
import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;

public class CreateNewSubject implements Command{

	@Override
	public Response execute(Request request) throws CommandException {

		CreateNewSubjectRequest req;

		if (request instanceof CreateNewSubjectRequest) {
			req = (CreateNewSubjectRequest) request;
		} else {
			throw new CommandException("Неверный запрос");
		}
		Response response = new Response();
		
		String subjectName = req.getSubjName();
		try {
			if (DAOFactory.getInstance().getTestingSystem().createNewSubject(subjectName)) {

				response.setErrorStatus(false);
				response.setResultMessage("Предмет добавлен");

			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Ошибка в добавлении предмета");
			}
		} catch (DAOException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;

		
	}

}
