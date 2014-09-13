package com.kiiolabs.cally.view;


import info.androidhive.slidingmenu.R;

import java.util.ArrayList;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kiiolabs.cally.controller.Controller;

public class MainContainer extends Fragment{
	
	private EditText txtField;

	private Button btnzero, btnone, btntwo, btnthree, btnfour, btnfive, btnsix,
			btnseven, btneight, btnnine, btnmultiply, btndivide, btnadd,
			btnsubtract, btndecimal, btnnewCal, btnLeftBracket,
			btnRightBracket, btnEqual, btnDelete;
	private ArrayList<Button> buttonArray = new ArrayList<Button>();
	private int layout;
	public void initializeLayout(int layout){
		this.layout= layout;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(layout, container,
				false);
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
		btnRightBracket = (Button) rootView
				.findViewById(R.id.rightBracket);
		btnDelete = (Button) rootView.findViewById(R.id.delete);
		
		setEditTextProperties();
		addToArray();
		setFontText();
		setUpListeners();
		return rootView;
	}
	public void setEditTextProperties(){
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
	public void addToArray(){
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
		button.setOnClickListener(new Controller(button, getActivity().getApplicationContext(), txtField));
		}
	}
}
