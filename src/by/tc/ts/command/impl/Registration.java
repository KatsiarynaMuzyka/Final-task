package by.tc.ts.command.impl;

import by.tc.ts.bean.RegistrationRequest;
import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;

public class Registration implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		RegistrationRequest req;

		if (request instanceof RegistrationRequest) {
			req = (RegistrationRequest) request;
		} else {
			throw new CommandException("Неверный запрос");
		}

		Response response = new Response();
		String login = req.getLogin();
		String password = req.getPassword();

		try {
			if (DAOFactory.getInstance().getUserDAO().registration(login, password)) {
				response.setErrorStatus(false);
				response.setResultMessage("Добро пожаловать, " + login);
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Невозможно добавить пользователя с логином " + login + " и паролем " + password);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

}
