package by.tc.ts.bean;

public class CreateNewSubjectRequest extends Request{

	private String subjName;

	public String getSubjName() {
		return subjName;
	}

	public void setSubjName(String subjName) {
		this.subjName = subjName.toUpperCase();
	}
	
}
