package by.tc.ts.service;

import java.util.List;

import by.tc.ts.service.exception.ServiceException;

public interface TestingSystemService {

	public List<String> showQuestionList(String subjName) throws ServiceException;

	public List<String> showSubjectList() throws ServiceException;

	public Object[][] startTest(String subjName) throws ServiceException;

}
