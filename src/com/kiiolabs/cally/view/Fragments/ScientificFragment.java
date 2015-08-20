package com.kiiolabs.cally.view.Fragments;

import java.util.ArrayList;

import com.kiiolabs.cally.R;
import com.kiiolabs.cally.controller.GlobalButtonListener;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.ActionMode.Callback;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

public class ScientificFragment extends Fragment {

	private static EditText txtField;
	private RelativeLayout navigationLayout;
	private DrawerLayout drawerLayout;
	private ImageButton btnDelete,navigationOptionButton;
	private ArrayList<Button> buttonArray = new ArrayList<Button>();
	private TableLayout tableLayout;
	private static String Key_STORE = "Key_Store";
	private static String Key_Color_Scheme = "Scheme_Layout";

	public static ScientificFragment InstanceOf(int colourscheme) {
		ScientificFragment container = new ScientificFragment();
		Bundle bundle = new Bundle();
		if (txtField != null) {
			bundle.putString(Key_STORE, txtField.getText().toString());
		}
		bundle.putInt(Key_Color_Scheme, colourscheme);

		container.setArguments(bundle);
		return container;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		int colour_scheme = getArguments().getInt(Key_Color_Scheme);

		View rootView = inflater.inflate(R.layout.scientific_fragment, container, false);
		tableLayout = (TableLayout) rootView.findViewById(R.id.tableLayout);
		txtField = (EditText) rootView.findViewById(R.id.txtField);
		btnDelete = (ImageButton) rootView.findViewById(R.id.delete);
		drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
		navigationOptionButton = (ImageButton) rootView.findViewById(R.id.navigationOptionButton);
		navigationLayout = (RelativeLayout) rootView.findViewById(R.id.navigation);

		String value = getArguments().getString(Key_STORE);
		tableLayout.setBackgroundColor(getResources().getColor(colour_scheme));
		txtField.setHintTextColor(getResources().getColor(colour_scheme));
		txtField.setTextColor(getResources().getColor(colour_scheme));
		if (value == null) {
			value = "0";
			txtField.setHint(String.valueOf(value));
		} else {
			txtField.setText(String.valueOf(value));
		}
		navigationLayout.setBackgroundColor(getResources().getColor(colour_scheme));
		setEditTextProperties();
		addToArray((ViewGroup) rootView);
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

	public void addToArray(ViewGroup viewChildren) {

		View child;
		for (int i = 0; i < viewChildren.getChildCount(); i++) {
			child = viewChildren.getChildAt(i);
			boolean buttonInstance = child instanceof Button;
			if (buttonInstance) {
				int ID = child.getId();
				switch (ID) {
				case R.id.squareCubeButton:
					((Button) child).setText(Html.fromHtml(getString(R.string.cube)));
					break;
				case R.id.squareRootButton:
					((Button) child).setText(Html.fromHtml(getString(R.string.square_root)));
					break;
				case R.id.squareButton:
					((Button) child).setText(Html.fromHtml(getString(R.string.square)));
					break;
				case R.id.squareyButton:
					((Button) child).setText(Html.fromHtml(getString(R.string.square_y)));
					break;
				}
				buttonArray.add((Button) child);
			} else if (child instanceof ViewGroup) {
				addToArray((ViewGroup) child);
			}
		}

	}

	public void setFontText() {
		Typeface font = Typeface.createFromAsset(this.getActivity().getApplicationContext().getAssets(),
				"MankSans-Medium.ttf");
		for (Button button : buttonArray) {
			button.setTypeface(font);
		}
		txtField.setTypeface(font);
	}

	public void setUpListeners() {
		navigationOptionButton.setOnClickListener(new NavigationListener());
		for (Button button : buttonArray) {
			button.setOnClickListener(new GlobalButtonListener(button, getActivity().getApplicationContext(), txtField));
			btnDelete.setOnClickListener(new GlobalButtonListener(button, getActivity().getApplicationContext(), txtField));
		}
	}
	private class NavigationListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			drawerLayout.openDrawer(Gravity.END);
		}

	}
}
