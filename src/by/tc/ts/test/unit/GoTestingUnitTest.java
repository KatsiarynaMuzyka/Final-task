package by.tc.ts.test.unit;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.tc.ts.dao.exception.DAOException;
import by.tc.ts.dao.factory.DAOFactory;
import by.tc.ts.test.unit.dataProvider.MyDataProvider;

public class GoTestingUnitTest extends Assert{

	
	List<Integer> actual = new ArrayList<>();
	
	@Test(dataProvider = "goTestingPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String subjectName, List<Integer> answer) throws DAOException{

		
		actual = DAOFactory.getInstance().getTestingSystem().startTest(subjectName);
		
		assertEquals(actual, answer, "I hope that work");
	}
	
	@Test(dataProvider = "goTestingNegativeTest", dataProviderClass = MyDataProvider.class)
	public void negativTest(String subjectName, List<Integer> answer) throws DAOException{
	
		actual = DAOFactory.getInstance().getTestingSystem().startTest(subjectName);
		
		assertNotEquals(actual, answer, "I hoped that doesn't work");
		
	}
	
	
}
