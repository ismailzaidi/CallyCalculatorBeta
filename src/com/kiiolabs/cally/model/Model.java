package com.kiiolabs.cally.model;

import java.text.DecimalFormat;

import org.nfunk.jep.JEP;

import com.kiiolabs.cally.R;

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

	public Model(Context context) {
		super();
		this.context = context;
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
			if (Double.compare(Double.NaN, resultFromParser) != 0) {
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
				textField.setHint("Syntax Error");
			}
		}
		if (button.getText().equals("C")) {
			textField.setText("");
			textField.setHint(R.string.zero);
		}
	}

}
