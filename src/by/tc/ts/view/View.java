package by.tc.ts.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.tc.ts.bean.CreateNewQuestionRequest;
import by.tc.ts.bean.CreateNewSubjectRequest;
import by.tc.ts.bean.LoginationRequest;
import by.tc.ts.bean.RegistrationRequest;
import by.tc.ts.bean.Request;
import by.tc.ts.bean.Response;
import by.tc.ts.bean.ShowQuestionListRequest;
import by.tc.ts.bean.StartTestRequest;
import by.tc.ts.bean.entity.UserKey;
import by.tc.ts.controller.Controller;

public class View {

	static Scanner in = new Scanner(System.in);
	private static boolean exit = true;
	private static String help = "0 - �����\n1 - �����������\n2 - ����\n3 - �������� ��������\n4 - ������ ����\n5 - �������� �������\n6 - ������� ����� ������\n7 - �������� ����� �������\n\'������\' - ������";

	private final static String registration = "REGISTRATION";
	private final static String logination = "LOGINATION";
	private final static String showSubject = "SHOW_SUBJECT";
	private final static String startTest = "START_TEST";
	private final static String showQuestion = "SHOW_QUESTION";
	private final static String createNewQuestion = "CREATE_NEW_QUESTION";
	private final static String createNewSubject = "CREATE_NEW_SUBJECT";

	public static void main(String[] args) {

		String login;
		String pass;
		String subjName;
		String question;
		int answer;
		String subjectName;

		Controller controller = new Controller();
		System.out.println(help);

		while (exit) {
			System.out.println("�������� ��������");
			String choise = in.nextLine();

			if (choise.equals("0")) {
				System.out.println("�� ��������!");
				in.close();
				break;
			}
			switch (choise) {

			case "help":
				System.out.println(help);
				break;

			case "1":
				RegistrationRequest regReq = new RegistrationRequest();
				regReq.setCommandName(registration);
				System.out.println("������� �����");
				login = in.nextLine();
				System.out.println("������� ������");
				pass = in.nextLine();
				regReq.setLogin(login);
				regReq.setPassword(pass);
				Response regResponse = controller.doRequest(regReq);
				if (regResponse.isErrorStatus() == true) {
					System.out.println(regResponse.getErrorMessage());
				} else {
					System.out.println(regResponse.getResultMessage());
				}
				break;

			case "2":
				LoginationRequest authReq = new LoginationRequest();
				authReq.setCommandName(logination);
				System.out.println("������� �����");
				login = in.nextLine();
				System.out.println("������� ������");
				pass = in.nextLine();
				authReq.setLogin(login);
				authReq.setPassword(pass);
				Response authResponse = controller.doRequest(authReq);
				if (authResponse.isErrorStatus() == true) {
					System.out.println(authResponse.getErrorMessage());
				} else {
					System.out.println(authResponse.getResultMessage());
				}
				break;

			case "3":
				if (UserKey.getUserKey() == 0) {
					System.out.println("������� ������� ��� �����������������");
					break;
				}
				List<String> showSubjList = new ArrayList<>();
				Request showSubRequest = new Request();
				showSubRequest.setCommandName(showSubject);
				Response showSubResponse = controller.doRequest(showSubRequest);
				if (showSubResponse.isErrorStatus() == true) {
					System.out.println(showSubResponse.getErrorMessage());
				} else {

					showSubjList = showSubResponse.getShowSubjList();

					for (String sub : showSubjList) {

						System.out.println(sub);
						System.out.println();
					}

					System.out.println(showSubResponse.getResultMessage());
				}
				break;

			case "4":
				if (UserKey.getUserKey() == 0) {
					System.out.println("������� ������� ��� �����������������");
					break;
				}

				int ansNum;
				Object test[][] = new Object[1][2];
				List<Integer> testAnswer = new ArrayList<>();
				List<String> testList = new ArrayList<>();
				List<Integer> chek = new ArrayList<Integer>();

				StartTestRequest goRequest = new StartTestRequest();
				goRequest.setCommandName(startTest);
				System.out.println("�������� �������");
				subjName = in.nextLine();
				goRequest.setSubjName(subjName);
				Response goResponse = controller.doRequest(goRequest);
				if (goResponse.isErrorStatus() == true) {
					System.out.println(goResponse.getErrorMessage());
				} else {

					test = goResponse.getTest();
					testAnswer = (List<Integer>) test[0][0];
					testList = (List<String>) test[0][1];

					for (int i = 0; i < testAnswer.size(); i++) {
						System.out.println(testList.get(i));
						System.out.println();
						System.out.println("�������� �����");
						ansNum = in.nextInt();
						chek.add(ansNum);
					}

					int point = 0;
					double mark = 0;

					for (int i = 0; i < testAnswer.size(); i++) {
						if (testAnswer.get(i) == chek.get(i)) {
							point++;
						}
					}
					mark = (double) point / testAnswer.size() * 100;
					System.out.println("���� ������: " + mark + " �� 100.0");

					System.out.println(goResponse.getResultMessage());
				}
				break;

			case "5":
				if (UserKey.getUserKey() == 0) {
					System.out.println("������� ������� ��� �����������������");
					break;
				} else if (UserKey.getUserKey() != 1) {
					System.out.println("� ��� ��� �������");
					break;
				}
				List<String> showQuestList = new ArrayList<>();
				ShowQuestionListRequest showTestReq = new ShowQuestionListRequest();
				showTestReq.setCommandName(showQuestion);
				System.out.println("������� ��� ��������");
				subjName = in.nextLine();
				showTestReq.setSubjName(subjName);
				Response showTestResponse = controller.doRequest(showTestReq);
				if (showTestResponse.isErrorStatus() == true) {
					System.out.println(showTestResponse.getErrorMessage());
				} else {
					showQuestList = showTestResponse.getShowQuestList();

					for (String quest : showQuestList) {
						System.out.println(quest);
						System.out.println();
					}

					System.out.println(showTestResponse.getResultMessage());
				}
				break;

			case "6":
				if (UserKey.getUserKey() == 0) {
					System.out.println("������� ������� ��� �����������������");
					break;
				} else if (UserKey.getUserKey() != 1) {
					System.out.println("� ��� ��� �������");
					break;
				}
				CreateNewQuestionRequest setNewQuestReq = new CreateNewQuestionRequest();
				setNewQuestReq.setCommandName(createNewQuestion);
				System.out.println("������� �������� ��������");
				String subject = in.nextLine();
				System.out.println("�������� ������");
				String quest = in.nextLine();
				System.out.println("������ ������� ������");
				String first = in.nextLine();
				System.out.println("������ ������� ������");
				String second = in.nextLine();
				System.out.println("������ ������� ������");
				String third = in.nextLine();
				System.out.println("��������� ������� ������");
				String last = in.nextLine();

				question = quest + "\n1. " + first + "\n2. " + second + "\n3. " + third + "\n4. " + last;

				System.out.println("����� ����������� ������");
				answer = Integer.parseInt(in.nextLine());
				setNewQuestReq.setAnswer(answer);
				setNewQuestReq.setQuestion(question);
				setNewQuestReq.setSubjName(subject);
				Response setNewQuestResponse = controller.doRequest(setNewQuestReq);
				if (setNewQuestResponse.isErrorStatus() == true) {
					System.out.println(setNewQuestResponse.getErrorMessage());
				} else {
					System.out.println(setNewQuestResponse.getResultMessage());
				}
				break;

			case "7":
				if (UserKey.getUserKey() == 0) {
					System.out.println("������� ������� ��� �����������������");
					break;
				} else if (UserKey.getUserKey() != 1) {
					System.out.println("� ��� ��� �������");
					break;
				}
				CreateNewSubjectRequest setSubReq = new CreateNewSubjectRequest();
				setSubReq.setCommandName(createNewSubject);
				System.out.println("������� �������� ��������");
				subjectName = in.nextLine();
				setSubReq.setSubjName(subjectName);
				Response setSubResponse = controller.doRequest(setSubReq);
				if (setSubResponse.isErrorStatus() == true) {
					System.out.println(setSubResponse.getErrorMessage());
				} else {
					System.out.println(setSubResponse.getResultMessage());
				}
				break;

			}

		}
	}
}
