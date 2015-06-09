package com.kiiolabs.cally.controller;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;

import com.kiiolabs.cally.R;
import com.kiiolabs.cally.model.Model;

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

	public Controller(Button button, Context context, EditText textField) {
		super();
		this.button = button;
		this.context = context;
		this.textField = textField;
		model = new Model(context);
		SetOnText();

	}

	public void SetOnText() {
		String savedValue = model.loadSavedPreferences();
		if (!savedValue.equals("0") && savedValue != null) {
			this.textField.setText(savedValue);
		}
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
				model.savePreferences(strBuilder.toString());
			}
		} else {
			this.textField.append(this.button.getText().toString());
			model.setDataToEditText(this.button,str, this.textField);
			model.savePreferences(this.textField.getText().toString());
		}
	}

}
