package by.tc.ts.command;


import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.command.exception.CommandException;

public interface Command {
	Response execute(Request request) throws CommandException;
}
