package by.tc.ts.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import by.tc.ts.dao.TestingSystemDAO;
import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.source.SQLConnection;


public class TestingSystemDAOImpl implements TestingSystemDAO {
	
	List<String> tempList = new ArrayList<>();
	List<Integer> answerList = new ArrayList<>();
	

	@Override
	public boolean createNewSubject(String subjName) throws DAOException {

		Connection con = null;
		Statement st = null;
		try {
			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();

			int result = st
					.executeUpdate("INSERT INTO subjects(subject_name) VALUES('" + subjName.toUpperCase() + "');");
			st.close();
			if (result != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					SQLConnection.getInstance().returnConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

	@Override
	public boolean createNewQuestion(String question, int answer, String subjName) throws DAOException {

		Connection con = null;

		try {
			con = SQLConnection.getInstance().getConnection();
			try (Statement st = con.createStatement()) {

				int result = st.executeUpdate("INSERT INTO questions(subject_name, question, answer) VALUES('" + subjName
						+ "','" + question + "','" + answer + "');");
				st.close();
				if (result != 0) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (con != null) {
				try {
					SQLConnection.getInstance().returnConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<String> showQuestionList(String subjName) throws DAOException {

		Connection con = null;
		Statement st = null;
		tempList.clear();
		try {
			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM questions WHERE subject_name = '" + subjName + "';");
			if (result.next() == false) {
				System.out.println("Вопросов не найдено");
				st.close();
				return null;
			} else {
				do {
					tempList.add(result.getString("question"));
				} while (result.next());
				st.close();
			}
			return tempList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					SQLConnection.getInstance().returnConnection(con);
				} catch (SQLException e) {

					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

		return null;
	}

	@Override
	public List<String> showSubjList() throws DAOException {

		Connection con = null;
		Statement st = null;
		tempList.clear();
		try {
			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM subjects;");
			if (result.next() == false) {
				System.out.println("Предметов не найдено");
				st.close();
				return null;
			} else {
				do {
					tempList.add(result.getString("subject_name"));
				} while (result.next());
				st.close();
			}
			return tempList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					SQLConnection.getInstance().returnConnection(con);
				} catch (SQLException e) {

					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
		return null;
	}

	@Override
	public List<Integer> startTest(String subjName) throws DAOException {

		Connection con = null;
		Statement st = null;
		answerList.clear();
		try {
			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM questions WHERE subject_name = '" + subjName + "';");
			if (result.next() == false) {
				System.out.println("Ответов не найдено");
				st.close();
				return null;
			} else {
				do {
					answerList.add(result.getInt("answer"));
				} while (result.next());
				st.close();
			}
			return answerList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					SQLConnection.getInstance().returnConnection(con);
				} catch (SQLException e) {

					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

		return null;
	
	}
	
	@Override
	public void deleteAllQuestions() throws DAOException{
		Connection con = null;
		Statement st = null;
		
		try {
			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();
			int testKILL = st.executeUpdate("DELETE FROM questions Where id>=0");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deleteAllSubjects() throws DAOException{
		Connection con = null;
		Statement st = null;
		
		try {
			con = SQLConnection.getInstance().getConnection();
			st = con.createStatement();
			int subDEL = st.executeUpdate("DELETE FROM subjects Where id>=0");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
