package com.kiiolabs.cally.model;

import org.nfunk.jep.function.Exp;

import android.util.Log;

public class MathamaticalFunctions {
	private String expression;
	private Double result;

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

	public int getMod() {
		Log.v("getMod()", expression);
		expression = expression.replaceAll("\\s+", "");
		int value1 = Integer.parseInt(expression.split("mod")[0]);
		int value2 = Integer.parseInt(expression.split("mod")[1]);
		return (value1 % value2);
	}

	public int getTenMultiplier() {
		return (int) Math.pow(10,Double.parseDouble(expression));
	}

	public double getSquareRoot() {
		return Math.sqrt(Double.parseDouble(expression));
	}
	public double getSquare() {
		return Math.pow(Double.parseDouble(expression), 2);
	}

	public double getCubeSquare() {
		return Math.cbrt(Double.parseDouble(expression));
	}

	public double getCube() {
		return Math.pow(Double.parseDouble(expression), 3);
	}

	public double getSquareY() {
		expression = expression.replaceAll("\\s+", "");
		double value1 = Double.parseDouble(expression.split("pow")[0]);
		double value2 = Double.parseDouble(expression.split("pow")[1]);
		return Math.pow(value1, value2);
	}

	public double getSin() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return Math.sin(toDegree);
	}

	public double getCos() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return  Math.cos(toDegree);
	}

	public double getTan() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return  Math.tan(toDegree);
	}

	public double getSinh() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return  Math.sinh(toDegree);
	}

	public double getCosh() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return  Math.cosh(toDegree);
	}

	public double getTanh() {
		double toDegree = Math.toRadians(Double.parseDouble(expression));
		return  Math.tanh(toDegree);
	}
	public double getExp(){
		
		return Math.exp(Double.parseDouble(expression));
	}
	/**
	 * Taken from tutorialPoint website Credit to them
	 * 
	 * @param number
	 * @return
	 */
	public long getFactorial() {
		long num = Long.parseLong(expression);
		long result = 1;
		if (num == 0) {
			return 1;
		} else

		{
			for (int i = 2; i <= num; i++) {
				result *= i;
			}
			return result;
		}
	}

	public double getLog() {
		return Math.log(Double.parseDouble(expression));
	}

}
