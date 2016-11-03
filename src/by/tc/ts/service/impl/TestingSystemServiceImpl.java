package by.tc.ts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;
import by.tc.ts.service.TestingSystemService;
import by.tc.ts.service.exception.ServiceException;

public class TestingSystemServiceImpl implements TestingSystemService {

	Scanner in = new Scanner(System.in);

	@Override
	public List<String> showQuestionList(String subjName) throws ServiceException {
		
		subjName = subjName.trim();

		if (subjName == null || subjName.length() < 1) {
			throw new ServiceException("Введите корректное имя предмета");
		}
		
		List<String> showQuestList = new ArrayList<>();
		
		showQuestList.clear();

		try {
			showQuestList = DAOFactory.getInstance().getTestingSystem().showQuestionList(subjName);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		if (showQuestList != null) {
			return showQuestList;
		} else {
			return null;
		}
	}

	@Override
	public List<String> showSubjectList() throws ServiceException {

		List<String> showSubjList = new ArrayList<>();
		showSubjList.clear();
		try {
			showSubjList = DAOFactory.getInstance().getTestingSystem().showSubjList();

			if (showSubjList != null) {
				return showSubjList;
			} else {
				return null;
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Object[][] startTest(String subjName) throws ServiceException {

		subjName = subjName.trim();
		
		if (subjName == null || subjName.length() < 1) {
			throw new ServiceException("Введите корректное имя предмета");
		}

		Object test[][] = new Object[1][2];
		List<Integer> answer = new ArrayList<>();
		List<String> testList = new ArrayList<>();
				

		answer.clear();
		testList.clear();
		try {

			answer = DAOFactory.getInstance().getTestingSystem().startTest(subjName);
			testList = DAOFactory.getInstance().getTestingSystem().showQuestionList(subjName);

			test[0][0] = answer;
			test[0][1] = testList;

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return test;
	}

}
