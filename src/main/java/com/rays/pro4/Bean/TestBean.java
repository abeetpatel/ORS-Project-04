package com.rays.pro4.Bean;

import com.google.protobuf.Timestamp;

public class TestBean extends BaseBean {

	private String testName;
	private String description;
	private Timestamp timeDuration;
	private int noOfQuestion;
	private String runTest;

	public TestBean() {
		// TODO Auto-generated constructor stub
	}

	public Timestamp getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(Timestamp timeDuration) {
		this.timeDuration = timeDuration;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNoOfQuestion() {
		return noOfQuestion;
	}

	public void setNoOfQuestion(int noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}

	public String getRunTest() {
		return runTest;
	}

	public void setRunTest(String runTest) {
		this.runTest = runTest;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
