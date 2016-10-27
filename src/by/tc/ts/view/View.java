package by.tc.ts.view;

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
				regReq.setCommandName("REGISTRATION");
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
				authReq.setCommandName("LOGINATION");
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
				Request showSubRequest = new Request();
				showSubRequest.setCommandName("SHOW_SUBJECT");
				Response showSubResponse = controller.doRequest(showSubRequest);
				if (showSubResponse.isErrorStatus() == true) {
					System.out.println(showSubResponse.getErrorMessage());
				} else {
					System.out.println(showSubResponse.getResultMessage());
				}
				break;

			case "4":
				if (UserKey.getUserKey() == 0) {
					System.out.println("������� ������� ��� �����������������");
					break;
				}
				StartTestRequest goRequest = new StartTestRequest();
				goRequest.setCommandName("START_TEST");
				System.out.println("�������� �������");
				subjName = in.nextLine();
				goRequest.setSubjName(subjName);
				Response goResponse = controller.doRequest(goRequest);
				if (goResponse.isErrorStatus() == true) {
					System.out.println(goResponse.getErrorMessage());
				} else {
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
				ShowQuestionListRequest showTestReq = new ShowQuestionListRequest();
				showTestReq.setCommandName("SHOW_QUESTION");
				System.out.println("������� ��� ��������");
				subjName = in.nextLine();
				showTestReq.setSubjName(subjName);
				Response showTestResponse = controller.doRequest(showTestReq);
				if (showTestResponse.isErrorStatus() == true) {
					System.out.println(showTestResponse.getErrorMessage());
				} else {
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
				setNewQuestReq.setCommandName("CREATE_NEW_QUESTION");
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
				setSubReq.setCommandName("CREATE_NEW_SUBJECT");
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
