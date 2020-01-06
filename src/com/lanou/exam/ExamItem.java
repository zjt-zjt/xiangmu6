package com.lanou.exam;

public class ExamItem {
	private String title;		//题目
	private String optionA;		//选项A
	private String optionB;		//选项B
	private String optionC;		//选项C
	private String optionD;		//选项D
	private String answer;		//答案
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public ExamItem() {
		super();
	}
	public ExamItem(String title, String optionA, String optionB, String optionC, String optionD, String answer) {
		super();
		this.title = title;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.answer = answer;
	}
	@Override
	public String toString() {
		return title + "\n" + optionA + "\n" + optionB + "\n" + optionC
				+ "\n" + optionD;
	}
	
	
}
