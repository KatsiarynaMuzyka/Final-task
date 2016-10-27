package by.tc.ts.bean.entity;

public class UserKey {

	private static int userKey;

	public static int getUserKey() {
		return userKey;
	}

	public static void setUserKey(int userKey) {
		UserKey.userKey = userKey;
	}
	
}
