package by.tc.ts.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;
import by.tc.ts.service.TestingSystemService;
import by.tc.ts.service.exception.ServiceException;

public class TestingSystemServiceImpl implements TestingSystemService{

	List<String> list = new ArrayList<>();
	List<Integer> answer = new ArrayList<>();
	List<Integer> chek = new ArrayList<Integer>();
	Scanner in = new Scanner(System.in);
	
	@Override
	public boolean showQuestionList(String subjName) throws ServiceException {

		if (subjName == null||subjName.equals("")||subjName.equals(" ")) {
			throw new ServiceException("Введите корректный номер предмета");
		}
		list.clear();
		try {
			list = DAOFactory.getInstance().getTestingSystem().showQuestionList(subjName);
			for (String quest : list) {
				System.out.println(quest);
				System.out.println();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}

		if (list != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean showSubjectList() throws ServiceException {

		list.clear();
		try {
			list = DAOFactory.getInstance().getTestingSystem().showSubjList();
			for (String sub : list) {
				
				System.out.println(sub);
				System.out.println();
			}
			if (list != null) {
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean startTest(String subjName) throws ServiceException {

		int choise;

		answer.clear();
		list.clear();
		try {

			answer = DAOFactory.getInstance().getTestingSystem().startTest(subjName);
			list = DAOFactory.getInstance().getTestingSystem().showQuestionList(subjName);

		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}

		for (int i = 0; i < answer.size(); i++) {
			System.out.println(list.get(i));
			System.out.println();
			System.out.println("Сделайте выбор");
			choise = in.nextInt();
			chek.add(choise);
		}

		int point = 0;
		double mark = 0;

		for (int i = 0; i < answer.size(); i++) {
			if (answer.get(i) == chek.get(i)) {
				point++;
			}
		}
		mark = (double) point / answer.size() * 100;
		System.out.println("Ваша оценка: " + mark + " из 100.0");

		return true;
	}

}
