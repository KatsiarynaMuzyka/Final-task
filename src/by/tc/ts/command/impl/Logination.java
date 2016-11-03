package by.tc.ts.command.impl;

import by.tc.ts.bean.LoginationRequest;
import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;

public class Logination implements Command{

	@Override
	public Response execute(Request request) throws CommandException {

		LoginationRequest req;

		if (request instanceof LoginationRequest) {
			req = (LoginationRequest) request;
		} else {
			throw new CommandException("Неверный запрос");
		}

		Response response = new Response();

		String login = req.getLogin();
		String password = req.getPassword();

		try {
			if (DAOFactory.getInstance().getUserDAO().logination(login, password)) {
				response.setErrorStatus(false);
				response.setResultMessage("Добро пожаловать, " + login);
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("В базе отсутствует пользователь с логином " + login + " и паролем " + password);
			}
		} catch (DAOException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		
		return response;
		
	}
	
	

}
