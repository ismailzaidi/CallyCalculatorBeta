package com.kiiolabs.cally.model.bean;

public class HistoryCalculation {

	private int id;
	private String expression,answer, time;
	public HistoryCalculation(){}
	public HistoryCalculation(String expression, String answer, String time) {
		super();
		this.expression = expression;
		this.answer = answer;
		this.time = time;
	}
	public HistoryCalculation(int id, String expression, String answer, String time) {
		super();
		this.id = id;
		this.expression = expression;
		this.answer = answer;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
