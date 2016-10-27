package by.tc.ts.controller;

import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.command.Command;
import by.tc.ts.command.exception.CommandException;

public class Controller {

	private CommandHelper helper = new CommandHelper();

	public Controller() {
	}

	public Response doRequest(Request request) {

		String commandName = request.getCommandName();

		Command command = helper.getCommand(commandName);

		Response response;
		try {
			response = command.execute(request);
		} catch (CommandException e) {
			response = new Response();
			response.setErrorStatus(true);
			response.setErrorMessage("ERROR!");
		}

		return response;

	}

}
