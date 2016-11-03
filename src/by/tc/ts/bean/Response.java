package by.tc.ts.bean;

import java.util.ArrayList;
import java.util.List;

public class Response {
	private boolean errorStatus;
	private String errorMessage;
	private String resultMessage;
	List<String> showQuestList = new ArrayList<String>();
	List<String> showSubjList = new ArrayList<>();
	Object test[][] = new Object[1][2];

	public Object[][] getTest() {
		return test;
	}

	public void setTest(Object[][] test) {
		this.test = test;
	}

	public List<String> getShowSubjList() {
		return showSubjList;
	}

	public void setShowSubjList(List<String> showSubjList) {
		this.showSubjList = showSubjList;
	}

	public List<String> getShowQuestList() {
		return showQuestList;
	}

	public void setShowQuestList(List<String> showQuestList) {
		this.showQuestList = showQuestList;
	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}
