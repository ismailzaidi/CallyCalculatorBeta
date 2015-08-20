package com.kiiolabs.cally.model;

import java.text.DecimalFormat;

import org.nfunk.jep.JEP;

import com.kiiolabs.cally.R;
import com.kiiolabs.cally.model.DB.DBHandler;
import com.kiiolabs.cally.model.bean.HistoryCalculation;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class Model {
	private Context context;
	private String KEY_VALUE = "com.kiiolabs.cally.value";
	private DBHandler handler;
	private Utility utiliy;

	public Model(Context context) {
		super();
		this.context = context;
		handler = new DBHandler(context);
		utiliy = new Utility(context);
	}

	public static double getResult(String input) {
		JEP parser = new JEP();
		parser.parseExpression(input);
		return parser.getValue();
	}

	public boolean dataFormatValidator(String input) {
		String pattern = "\\d+\\.0";
		if (input.matches(pattern)) {
			return true;
		} else {
			return false;
		}

	}
	public String loadSavedPreferences() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPreferences.getString(KEY_VALUE, "0");
	}

	public void savePreferences(String value) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putString(KEY_VALUE, value);
		editor.commit();
	}

	public void setDataToEditText(Button button, String str, EditText textField) {
		Log.v("Equal Button", button.getText().toString());
		Log.v("Equal Boolean", String.valueOf(button.getText().equals(R.string.equal)));
		
		if (button.getText().equals("=")) {
			str = str.replaceAll("x", "*");
			double resultFromParser = 0.0;
			resultFromParser = Model.getResult(str.replaceAll("=", ""));
			String result = "";
			if (Double.compare(Double.NaN, resultFromParser) != 0 || Double.compare(Double.POSITIVE_INFINITY, resultFromParser)!=0) {
				if (!dataFormatValidator(String.valueOf(resultFromParser))) {
					DecimalFormat decimalFormat = new DecimalFormat("0.00##");
					result = decimalFormat.format(resultFromParser);
				} else {
					int integerResult = (int) resultFromParser;
					result = String.valueOf(integerResult);
				}
				Log.v("SetDataToEdit", "Line 70" + result);
				textField.setText(result);
			} else {
				textField.setText("");
				textField.setHint("Invalid Operation");
			}
		}
		if (button.getText().equals("C")) {
			textField.setText("");
			textField.setHint(R.string.zero);
		}
	}
	public boolean errorHandler(String str) {
		if (str.contains("Syntax")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean removeScientificOccurance(String expression, char occurance) {
		char lastExpression = expression.charAt(expression.length() - 1);
		Log.v("removeOperatorOccurance", String.valueOf(lastExpression));
		Log.v("removeOperatorOccurance", String.valueOf(lastExpression == occurance));
		if (lastExpression == occurance) {
			return true;
		} else {
			return false;
		}
	}
	private boolean removeOperatorOccurance(String expression){
		char[] arrexpression = {'x','/','+','-'};
		char lastExpression = expression.charAt(expression.length() - 1);
		for (int i = 0; i < arrexpression.length; i++) {
			if( arrexpression[i] == lastExpression){
				return true;
			}
		}
		return false;
	}
	public void globalDataSave(String expression, String answer) {
		HistoryCalculation calculations = new HistoryCalculation();
		calculations.setExpression(expression);
		calculations.setAnswer(answer);
		calculations.setTime(utiliy.getCurrentTime());
		handler.addHistoryCalculations(calculations);
	}

}
