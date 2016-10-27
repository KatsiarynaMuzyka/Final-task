package by.tc.ts.service;

import by.tc.ts.service.impl.TestingSystemServiceImpl;

public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();

	private TestingSystemService testService = new TestingSystemServiceImpl();

	public static ServiceFactory getInstance() {
		return instance;
	}

	public TestingSystemService getTestingSystemService() {
		return testService;
	}

}
