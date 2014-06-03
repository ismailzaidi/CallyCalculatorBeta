package com.kiiolabs.cally.controller;

import info.androidhive.slidingmenu.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import bsh.EvalError;
import bsh.Interpreter;

public class Controller implements OnClickListener {

	private Button button;
	private Context context;
	private EditText textField;

	public Controller(Button button, Context context, EditText textField) {
		super();
		this.button = button;
		this.context = context;
		this.textField = textField;
		loadSavedPreferences();
	}

	public void onButtonEffect(View button) {
		button.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					v.getBackground().setColorFilter(0xe0232329,
							PorterDuff.Mode.SRC_ATOP);
					v.invalidate();
					break;
				}
				case MotionEvent.ACTION_UP: {
					v.getBackground().clearColorFilter();
					v.invalidate();
					break;
				}
				}
				return false;
			}
		});
	}

	@Override
	public void onClick(View v) {
		onButtonEffect(button);
		String str = this.textField.getText().toString();
		if (v.getId() == R.id.delete) {
			if(str.length()!=0){
			StringBuilder strBuilder = new StringBuilder(str);
			strBuilder= strBuilder.deleteCharAt(str.length()-1);
			this.textField.setText(strBuilder.toString());
			}
		} else {
			this.textField.append(this.button.getText().toString());
			if (this.textField.getText().equals("0.0")
					|| this.textField.getText().equals("0")) {
				this.textField.setText("");
			}
			if (this.button.getText().equals("=")) {
				str = str.replaceAll("x", "*");
				double result = 0.0;
				try {
					result = getResult(str.replaceAll("=", ""));
					String num = String.valueOf(result);
					System.out.println("String Contains:  " + num);

					System.out.println("Validation:  " + validator(num));
					if (validator(num)) {
						String intValue = num.replaceAll("\\.[0]", "");
						this.textField.setText(intValue);
						savePreferences("VALUE", intValue);
					} else {
						this.textField.setText(num);
						savePreferences("VALUE", num);
					}
				} catch (EvalError e) {
					// TODO Auto-generated catch block
					this.textField.setText("Syntax Error");
					e.printStackTrace();
				}

			} else if (this.button.getText().equals("C")) {
				this.textField.setText("");
				this.textField.setHint("0");
			}
		}
	}

	public static double getResult(String input) throws EvalError {
		Interpreter interpt = new Interpreter();
		double num = 0;
		interpt.eval("result =" + input);
		num = Double.parseDouble(String.valueOf(interpt.get("result")));
		System.out.println("Register: " + num);
		return num;
	}

	public boolean validator(String input) {
		String REGEX = "\\d+\\.[0]{1}";
		if (input.matches(REGEX)) {
			return true;
		} else {
			return false;
		}
	}

	private void loadSavedPreferences() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String name = sharedPreferences.getString("VALUE", "0");
		this.textField.setText(name);
	}

	private void savePreferences(String key, String value) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
}
