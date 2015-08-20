package com.kiiolabs.cally.model;

import java.security.InvalidAlgorithmParameterException;

import org.nfunk.jep.function.Exp;

import android.util.Log;

public class MathamaticalFunctions {
	private String expression;
	private Double result;
	private int MAX_INT = Integer.MAX_VALUE;
	private int MIN_INT = Integer.MIN_VALUE;
	private double MAX_DOUBLE = Double.MAX_VALUE;
	private double MIN_DOUBLE = Double.MIN_VALUE;
	private long MAX_LONG = Long.MAX_VALUE;
	private long MIN_LONG = Long.MIN_VALUE;

	public MathamaticalFunctions() {
		// TODO Auto-generated constructor stub
	}
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public String getMod() {
		Log.v("getMod()", expression);
		expression = expression.replaceAll("\\s+", "");
		
		int value1 = Integer.parseInt(expression.split("mod")[0]);
		int value2 = Integer.parseInt(expression.split("mod")[1]);
		return intValidator((value1 % value2));
	}

	public String getTenMultiplier() {
		String tenValue = intValidator((int) Math.pow(10,Double.parseDouble(expression)));
		return tenValue;
	}

	public String getSquareRoot() {
		return doubleValidator(Math.sqrt(Double.parseDouble(expression)));
	}
	public String getSquare() {
		return doubleValidator(Math.pow(Double.parseDouble(expression), 2));
	}

	public String getCubeSquare() {
		return doubleValidator(Math.cbrt(Double.parseDouble(expression)));
	}

	public String getCube() {
		return doubleValidator(Math.pow(Double.parseDouble(expression), 3));
	}

	public String getSquareY() {
		expression = expression.replaceAll("\\s+", "");
		double value1 = Double.parseDouble(expression.split("pow")[0]);
		double value2 = Double.parseDouble(expression.split("pow")[1]);
		return doubleValidator(Math.pow(value1, value2));
	}

	public String getSin() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return doubleValidator(Math.sin(toDegree));
	}

	public String getCos() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return  doubleValidator(Math.cos(toDegree));
	}

	public String getTan() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return  doubleValidator(Math.tan(toDegree));
	}

	public String getSinh() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return  doubleValidator(Math.sinh(toDegree));
	}

	public String getCosh() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return  doubleValidator(Math.cosh(toDegree));
	}

	public String getTanh() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return  doubleValidator(Math.tanh(toDegree));
	}
	public String getExp(){
		
		return doubleValidator(Math.exp(Double.parseDouble(expression)));
	}
	/**
	 * Taken from tutorialPoint website Credit to them
	 * 
	 * @param number
	 * @return
	 */
	public String getFactorial() {
		long num = Long.parseLong(expression);
		long result = 1;
		if (num == 0) {
			return String.valueOf(1);
		} else

		{
			for (int i = 2; i <= num; i++) {
				result *= i;
			}
			return longValidator(result);
		}
	}
	
	public String getLog() {
		return doubleValidator(Math.log(Double.parseDouble(expression)));
	}
	public String longValidator(long value){
		if(value>MAX_LONG || value < MIN_LONG){
			return "Syntax error";
		}else{
			return String.valueOf(value);
		}
	}
	public String intValidator(int value){
		if(value>MAX_INT || value < MIN_INT){
			return "Syntax error";
		}else{
			return String.valueOf(value);
		}
	}
	public String doubleValidator(double value){
		if(value>MAX_DOUBLE || value < MIN_DOUBLE){
			return "Syntax error";
		}else{
			return String.valueOf(value);
		}
	}

}
