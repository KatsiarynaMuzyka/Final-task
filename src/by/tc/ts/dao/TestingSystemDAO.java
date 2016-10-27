package by.tc.ts.dao;

import java.util.List;

import by.tc.ts.dao.exception.DAOException;

public interface TestingSystemDAO {

	public boolean createNewSubject(String subjName) throws DAOException;
	
	public boolean createNewQuestion(String question, int answer, String subjName) throws DAOException;
	
	public List<String> showQuestionList(String subjName) throws DAOException;
	
	public List<String> showSubjList() throws DAOException;
	
	public List<Integer> startTest(String subjName) throws DAOException;
	
	public void deleteAllQuestions() throws DAOException;
	
	public void deleteAllSubjects() throws DAOException;
	
}
