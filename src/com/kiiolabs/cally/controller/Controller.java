package com.kiiolabs.cally.controller;

import info.androidhive.slidingmenu.R;

import org.nfunk.jep.JEP;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;

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
		postToViews(str, v);
	}

	public void postToViews(String str, View v) {
		if (v.getId() == R.id.delete) {
			if (str.length() != 0) {
				StringBuilder strBuilder = new StringBuilder(str);
				strBuilder = strBuilder.deleteCharAt(str.length() - 1);
				this.textField.setText(strBuilder.toString());
				savePreferences("VALUE", strBuilder.toString());
			}
		} else {
			this.textField.append(this.button.getText().toString());
			if (this.button.getText().equals("=")) {
				str = str.replaceAll("x", "*");
				double result = 0.0;
				result = getResult(str.replaceAll("=", ""));
				// Exception Handler
				if (Double.compare(Double.NaN, result) != 0) {
					this.textField.setText(String.valueOf(result));
					savePreferences("VALUE", String.valueOf(result));
				} else {
					this.textField.setText("Syntax Error");
				}
				// TODO Auto-generated catch block
			} else if (this.button.getText().equals("C")) {
				String hint = "0";
				this.textField.setText("");
				this.textField.setHint(hint);
				savePreferences("VALUE", hint);
			}
		}
	}

	public static double getResult(String input) {
		JEP parser = new JEP();
		parser.parseExpression(input);
		return parser.getValue();
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
