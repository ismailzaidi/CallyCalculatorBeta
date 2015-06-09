package com.kiiolabs.cally.view;

import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kiiolabs.cally.R;
import com.kiiolabs.cally.controller.Controller;

public class MainContainer extends Fragment {

	private static EditText txtField;
	private Button btnzero, btnone, btntwo, btnthree, btnfour, btnfive, btnsix,
			btnseven, btneight, btnnine, btnmultiply, btndivide, btnadd,
			btnsubtract, btndecimal, btnnewCal, btnLeftBracket,
			btnRightBracket, btnEqual, btnDelete;
	private ArrayList<Button> buttonArray = new ArrayList<Button>();
	private static String Key_STORE = "Key_Store";
	private static String Key_Layout = "Key_Layout";

	
	public static MainContainer InstanceOf(int layout) {
		MainContainer container = new MainContainer();
		Bundle bundle = new Bundle();
		if (txtField != null) {
			bundle.putString(Key_STORE, txtField.getText().toString());
		}
		bundle.putInt(Key_Layout, layout);
		container.setArguments(bundle);
		return container;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		int layout = getArguments().getInt(Key_Layout);
		
		View rootView = inflater.inflate(layout, container, false);
		txtField = (EditText) rootView.findViewById(R.id.txtField);
		btnzero = (Button) rootView.findViewById(R.id.zero);
		btnone = (Button) rootView.findViewById(R.id.one);
		btntwo = (Button) rootView.findViewById(R.id.two);
		btnthree = (Button) rootView.findViewById(R.id.three);
		btnfour = (Button) rootView.findViewById(R.id.four);
		btnfive = (Button) rootView.findViewById(R.id.five);
		btnsix = (Button) rootView.findViewById(R.id.six);
		btnseven = (Button) rootView.findViewById(R.id.seven);
		btneight = (Button) rootView.findViewById(R.id.eight);
		btnnine = (Button) rootView.findViewById(R.id.nine);
		btnmultiply = (Button) rootView.findViewById(R.id.multiply);
		btndivide = (Button) rootView.findViewById(R.id.divide);
		btnadd = (Button) rootView.findViewById(R.id.add);
		btnsubtract = (Button) rootView.findViewById(R.id.subtract);
		btndecimal = (Button) rootView.findViewById(R.id.decimal);
		btnnewCal = (Button) rootView.findViewById(R.id.newCalc);
		btnEqual = (Button) rootView.findViewById(R.id.equal);
		btnLeftBracket = (Button) rootView.findViewById(R.id.leftBracket);
		btnRightBracket = (Button) rootView.findViewById(R.id.rightBracket);
		btnDelete = (Button) rootView.findViewById(R.id.delete);
		String value = getArguments().getString(Key_STORE);
		if(value == null){
			value = "0";
			txtField.setHint(String.valueOf(value));
		}else{
			txtField.setText(String.valueOf(value));
		}
		Log.v("Value", "Value of: "+value);
		setEditTextProperties();
		addToArray();
		setFontText();
		setUpListeners();
		return rootView;
	}

	public void setEditTextProperties() {
		txtField.setInputType(InputType.TYPE_NULL);
		txtField.setCustomSelectionActionModeCallback(new Callback() {

			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			public void onDestroyActionMode(ActionMode mode) {
			}

			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				return true;
			}

			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				return true;
			}
		});
	}

	public void addToArray() {
		buttonArray.add(btnzero);
		buttonArray.add(btnone);
		buttonArray.add(btntwo);
		buttonArray.add(btnthree);
		buttonArray.add(btnfour);
		buttonArray.add(btnfive);
		buttonArray.add(btnsix);
		buttonArray.add(btnseven);
		buttonArray.add(btneight);
		buttonArray.add(btnnine);
		buttonArray.add(btnmultiply);
		buttonArray.add(btndivide);
		buttonArray.add(btnadd);
		buttonArray.add(btnsubtract);
		buttonArray.add(btndecimal);
		buttonArray.add(btnnewCal);
		buttonArray.add(btnLeftBracket);
		buttonArray.add(btnRightBracket);
		buttonArray.add(btnEqual);
		buttonArray.add(btnDelete);
	}

	public void setFontText() {
		Typeface font = Typeface.createFromAsset(this.getActivity()
				.getApplicationContext().getAssets(), "MankSans-Medium.ttf");
		for (Button button : buttonArray) {
			button.setTypeface(font);
		}
		txtField.setTypeface(font);
	}

	public void setUpListeners() {
		for (Button button : buttonArray) {
			button.setOnClickListener(new Controller(button, getActivity()
					.getApplicationContext(), txtField));
		}
	}

	public EditText getTxtField() {
		return txtField;
	}
}
