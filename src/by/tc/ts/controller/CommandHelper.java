package by.tc.ts.controller;

import java.util.HashMap;
import java.util.Map;

import by.tc.ts.command.Command;
import by.tc.ts.command.impl.CreateNewQuestion;
import by.tc.ts.command.impl.CreateNewSubject;
import by.tc.ts.command.impl.Logination;
import by.tc.ts.command.impl.Registration;
import by.tc.ts.command.impl.ShowQuestionList;
import by.tc.ts.command.impl.ShowSubjectList;
import by.tc.ts.command.impl.StartTest;

public class CommandHelper {

	private Map<String, Command> commands = new HashMap<String, Command>();

	public CommandHelper() {

		commands.put("REGISTRATION", new Registration());
		commands.put("LOGINATION", new Logination());
		commands.put("SHOW_SUBJECT", new ShowSubjectList());
		commands.put("START_TEST", new StartTest());
		commands.put("SHOW_QUESTION", new ShowQuestionList());
		commands.put("CREATE_NEW_QUESTION", new CreateNewQuestion());
		commands.put("CREATE_NEW_SUBJECT", new CreateNewSubject());

	}

	public Command getCommand(String commandName) {

		Command command;

		command = commands.get(commandName);

		return command;
	}

}
