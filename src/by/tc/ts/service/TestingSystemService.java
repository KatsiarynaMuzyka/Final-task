package by.tc.ts.service;

import by.tc.ts.service.exception.ServiceException;

public interface TestingSystemService {

	public boolean showQuestionList(String subjName) throws ServiceException;

	public boolean showSubjectList() throws ServiceException;

	public boolean startTest(String subjName) throws ServiceException;

}
