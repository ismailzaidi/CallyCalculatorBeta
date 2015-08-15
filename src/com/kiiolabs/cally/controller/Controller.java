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
public class Controller implements OnClickListener {

	private Button button;
	private Context context;
	private EditText textField;
	private Model model;
	private MathamaticalFunctions math_functions;
	private Utility utiliy;
	private DBHandler handler;
	private HistoryCalculation calculations;

	public Controller(Button button, Context context, EditText textField) {
		super();
		this.button = button;
		this.context = context;
		this.textField = textField;
		model = new Model(context);
		utiliy = new Utility(context);
		math_functions = new MathamaticalFunctions();
		calculations = new HistoryCalculation();
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
				StringBuilder strBuilder = new StringBuilder(str);
				strBuilder = strBuilder.deleteCharAt(str.length() - 1);
				this.textField.setText(strBuilder.toString());
				model.savePreferences(strBuilder.toString());
			}
			break;
		case R.id.squareRootButton:
			if (checkInput()) {
				double result = math_functions.getSquareRoot();
				this.textField.setText(String.valueOf(result));
				
				globalDataSave("sqrt("+str+")", String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.squareButton:
			if (checkInput()) {
				double result = math_functions.getSquare();
				this.textField.setText(String.valueOf(result));
				globalDataSave("x^2 x = " +str, String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.expButton:
			if (checkInput()) {
				double result = math_functions.getExp();
				this.textField.setText(String.valueOf(result));
				globalDataSave("Ex = " +str, String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.factorialButton:
			if (checkInput()) {
				double result = math_functions.getFactorial();
				this.textField.setText(String.valueOf(result));
				globalDataSave("!n, n = " +str, String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.squareCubeButton:
			if (checkInput()) {
				double result = math_functions.getCube();
				this.textField.setText(String.valueOf(result));
				globalDataSave("x^3 x = " +str, String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.tenMultiplierButton:
			if (checkInput()) {
				double result = math_functions.getTenMultiplier();
				this.textField.setText(String.valueOf(result));
				globalDataSave("10x  x = " +str, String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.sinButton:
			if (checkInput()) {
				double result = math_functions.getSin();
				this.textField.setText(String.valueOf(result));
				globalDataSave("sin("+str+")", String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.cosButton:
			if (checkInput()) {
				double result = math_functions.getCos();
				this.textField.setText(String.valueOf(result));
				globalDataSave("cos("+str+")", String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.tanButton:
			if (checkInput()) {
				double result = math_functions.getTan();
				this.textField.setText(String.valueOf(result));
				globalDataSave("tan("+str+")", String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.sinHButton:
			if (checkInput()) {
				double result = math_functions.getSinh();
				this.textField.setText(String.valueOf(result));
				globalDataSave("sinH("+str+")", String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.cosHButton:
			if (checkInput()) {
				double result = math_functions.getCosh();
				this.textField.setText(String.valueOf(result));
				globalDataSave("cosH("+str+")", String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.tanHButton:
			if (checkInput()) {
				double result = math_functions.getTanh();
				this.textField.setText(String.valueOf(result));
				globalDataSave("tanH("+str+")", String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;
		case R.id.logButton:
			if (checkInput()) {
				double result = math_functions.getLog();
				this.textField.setText(String.valueOf(result));
				globalDataSave("Log("+str+")", String.valueOf(result));
			} else {
				refreshEditText();
			}
			break;

		case R.id.squareyButton:
			if (checkInput()) {
				double result = math_functions.getTenMultiplier();
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
				int value = math_functions.getMod();
				textField.setText(String.valueOf(value));
				model.savePreferences(this.textField.getText().toString());
				globalDataSave(str, String.valueOf(value));
			} else if (str.contains("pow")) {
				double value = math_functions.getSquareY();
				textField.setText(String.valueOf(value));
				model.savePreferences(this.textField.getText().toString());
				globalDataSave(str, String.valueOf(value));
			}

			else {
				str = str.replaceAll("x", "*");
				double resultFromParser = 0.0;
				resultFromParser = Model.getResult(str.replaceAll("=", ""));
				String result = "";
				if (Double.compare(Double.NaN, resultFromParser) != 0) {
					if (!model.dataFormatValidator(String.valueOf(resultFromParser))) {
						DecimalFormat decimalFormat = new DecimalFormat("0.00##");
						result = decimalFormat.format(resultFromParser);
					} else {
						int integerResult = (int) resultFromParser;
						result = String.valueOf(integerResult);
					}
					Log.v("SetDataToEdit", "Line 70" + result);
					textField.setText(result);
					globalDataSave(str, String.valueOf(result));
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
	private void globalDataSave(String expression, String answer){
		calculations.setExpression(expression);
		calculations.setAnswer(answer);
		calculations.setTime(utiliy.getCurrentTime());
		handler.addHistoryCalculations(calculations);
	}
}
