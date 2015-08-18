package com.kiiolabs.cally.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.DecimalFormat;

import com.kiiolabs.cally.R;
import com.kiiolabs.cally.model.MathamaticalFunctions;
import com.kiiolabs.cally.model.Model;
import com.kiiolabs.cally.model.Utility;
import com.kiiolabs.cally.model.DB.DBHandler;
import com.kiiolabs.cally.model.bean.HistoryCalculation;

/**
 * Controller for handelling user input
 * 
 * @author Ismail Zaidi
 *
 */
public class GlobalButtonListener implements OnClickListener {

	private Button button;
	private Context context;
	private EditText textField;
	private Model model;
	private MathamaticalFunctions math_functions;
	private Utility utiliy;
	private DBHandler handler;

	public GlobalButtonListener(Button button, Context context, EditText textField) {
		super();
		this.button = button;
		this.context = context;
		this.textField = textField;
		model = new Model(context);
		utiliy = new Utility(context);
		math_functions = new MathamaticalFunctions();
		handler = new DBHandler(context);
		SetOnText();

	}

	public void SetOnText() {
		String savedValue = model.loadSavedPreferences();
		if (!savedValue.equals("0") && savedValue != null) {
			this.textField.setText(savedValue);
		}
	}

	@Override
	public void onClick(View v) {
		String str = this.textField.getText().toString();
		math_functions.setExpression(str);
		postToViews(str, v);
	}

	public void postToViews(String str, View v) {
		int id = v.getId();
		switch (id) {
		case R.id.delete:
			if (str.length() != 0) {
				if (removeOperatorOccurance(str, 'w')) {
					str = str.replaceAll("\\spow", "");
					this.textField.setText(str);
				}else if(removeOperatorOccurance(str, 'd')){
					str = str.replaceAll("\\smod", "");
					this.textField.setText(str);
				}else{
					StringBuilder strBuilder = new StringBuilder(str);
					strBuilder = strBuilder.deleteCharAt(str.length() - 1);
					this.textField.setText(strBuilder.toString());
					model.savePreferences(strBuilder.toString());
				}
			}
			break;
		case R.id.squareRootButton:
			if (checkInput()) {
				String result = math_functions.getSquareRoot();
				postToEditText("sqrt(" + str + ")", String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.squareButton:
			if (checkInput()) {
				String result = math_functions.getSquare();
				postToEditText("x^2 x = " + str, String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.expButton:
			if (checkInput()) {
				String result = math_functions.getExp();
				postToEditText("Ex = " + str, String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.factorialButton:
			if (checkInput()) {
				String result = math_functions.getFactorial();
				postToEditText("!n, n = " + str, String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.squareCubeButton:
			if (checkInput()) {
				String result = math_functions.getCube();
				postToEditText("x^3 x = " + str, String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.tenMultiplierButton:
			if (checkInput()) {
				String result = math_functions.getTenMultiplier();
				postToEditText("10x  x = " + str, String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.sinButton:
			if (checkInput()) {
				String result = math_functions.getSin();
				postToEditText("sin(" + str + ")", String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.cosButton:
			if (checkInput()) {
				String result = math_functions.getCos();
				postToEditText("cos(" + str + ")", String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.tanButton:
			if (checkInput()) {
				String result = math_functions.getTan();
				postToEditText("tan(" + str + ")", String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.sinHButton:
			if (checkInput()) {
				String result = math_functions.getSinh();
				postToEditText("sinH(" + str + ")", String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.cosHButton:
			if (checkInput()) {
				String result = math_functions.getCosh();
				postToEditText("cosH(" + str + ")", String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.tanHButton:
			if (checkInput()) {
				String result = math_functions.getTanh();
				postToEditText("tanH(" + str + ")", String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;
		case R.id.logButton:
			if (checkInput()) {
				String result = math_functions.getLog();
				postToEditText("Log(" + str + ")", String.valueOf(result), "setText");
			} else {
				refreshEditText();
			}
			break;

		case R.id.squareyButton:
			if (checkInput()) {
				this.textField.append(" pow ");
			} else {
				refreshEditText();
			}
			break;
		case R.id.modButton:
			if (checkInput()) {
				this.textField.append(" mod ");
			} else {
				refreshEditText();
			}
			break;
		default:
			this.textField.append(this.button.getText().toString());
			setDataToEditText(this.button, str, this.textField);
			model.savePreferences(this.textField.getText().toString());
			break;
		}
	}

	public void setDataToEditText(Button button, String str, EditText textField) {
		if (button.getText().equals("=")) {
			if (str.contains("mod")) {
				String value = math_functions.getMod();
				textField.setText(String.valueOf(value));
				model.savePreferences(this.textField.getText().toString());
				globalDataSave(str, String.valueOf(value));
			} else if (str.contains("pow")) {
				String value = math_functions.getSquareY();
				textField.setText(String.valueOf(value));
				model.savePreferences(this.textField.getText().toString());
				globalDataSave(str, String.valueOf(value));
			} else {
				str = str.replaceAll("x", "*");
				double resultFromParser = 0.0;
				resultFromParser = Model.getResult(str.replaceAll("=", ""));
				String result = "";
				if (Double.compare(Double.NaN, resultFromParser) != 0) {
					postToEditText(str, String.valueOf(resultFromParser), "setText");
				} else {
					textField.setText("");
					textField.setHint("Syntax Error");
				}
			}
		}
		if (button.getText().equals("C")) {
			textField.setText("");
			textField.setHint(R.string.zero);
		}
	}

	private void refreshEditText() {
		this.textField.setText("");
		this.textField.setHint(R.string.zero);
	}

	public boolean checkInput() {
		String txt = this.textField.getText().toString();
		if (txt.equals("") || txt.contentEquals("(") || txt.contains(")")) {
			return false;
		} else {
			return true;
		}
	}

	private void postToEditText(String str, String answer, String type) {
		String result = answer;
		if (!str.contains("mod") || !str.contains("pow")) {
			if (!model.dataFormatValidator(String.valueOf(answer))) {
				Log.v("postToEditText", answer);
				DecimalFormat decimalFormat = new DecimalFormat("0.00##");
				result = decimalFormat.format(Double.parseDouble(answer));
			} else {
				int integerResult = (int) Double.parseDouble(answer);
				result = String.valueOf(integerResult);
			}
			globalDataSave(str, String.valueOf(result));
		}

		if (type.equals("append")) {
			this.textField.append(result);
		} else {
			this.textField.setText(result);
		}
	}

	public boolean removeOperatorOccurance(String expression, char occurance) {
		char lastExpression = expression.charAt(expression.length()-1);
		Log.v("removeOperatorOccurance", String.valueOf(lastExpression));
		Log.v("removeOperatorOccurance", String.valueOf(lastExpression==occurance));
		if (lastExpression==occurance) {
			return true;
		} else {
			return false;
		}
	}

	private void globalDataSave(String expression, String answer) {
		HistoryCalculation calculations = new HistoryCalculation();
		calculations.setExpression(expression);
		calculations.setAnswer(answer);
		calculations.setTime(utiliy.getCurrentTime());
		handler.addHistoryCalculations(calculations);
	}
}
