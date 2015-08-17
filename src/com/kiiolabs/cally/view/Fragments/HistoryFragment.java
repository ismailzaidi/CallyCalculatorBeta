package com.kiiolabs.cally.view.Fragments;

import java.util.ArrayList;
import java.util.Collections;

import com.kiiolabs.cally.R;
import com.kiiolabs.cally.adapter.CustomHistoryListViewAdapter;
import com.kiiolabs.cally.model.Utility;
import com.kiiolabs.cally.model.DB.DBHandler;
import com.kiiolabs.cally.model.bean.HistoryCalculation;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryFragment extends Fragment {
	private Context context;
	private CustomHistoryListViewAdapter adapter;
	private ListView listView;
	private RelativeLayout navigationLayout;
	private DrawerLayout drawerLayout;
	private TypedArray colour_list;
	private TextView clearAllTextView;
	private ImageButton navigationOptionButton;
	private Utility utils;
	private DBHandler db;
	private static String Colour_Key = "com.kiiolabs.cally.colour.history";

	public static HistoryFragment InstanceOf(int colourscheme) {
		HistoryFragment fragment = new HistoryFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(Colour_Key, colourscheme);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View colourScheme = inflater.inflate(com.kiiolabs.cally.R.layout.history_fragment, container, false);
		this.context = getActivity().getApplicationContext();
		db = new DBHandler(context);
		utils = new Utility(context);
		context = getActivity().getApplicationContext();
		int colour = getArguments().getInt(Colour_Key);

		intializeView(colourScheme);
		adapter = new CustomHistoryListViewAdapter(getActivity(), getColourScheme(), colour);
		listView.setAdapter(adapter);
		listView.setItemsCanFocus(true);
		listView.setOnItemLongClickListener(new ListViewListener());
		navigationOptionButton.setOnClickListener(new NavigationListener());
		clearAllTextView.setOnClickListener(new HistoryListener());
		navigationLayout.setBackgroundColor(getResources().getColor(colour));
		return colourScheme;
	}

	private void intializeView(View colourScheme) {
		drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
		navigationOptionButton = (ImageButton) colourScheme.findViewById(R.id.navigationOptionButton);
		navigationLayout = (RelativeLayout) colourScheme.findViewById(R.id.navigation);
		clearAllTextView = (TextView) colourScheme.findViewById(R.id.clearHistory);
		listView = (ListView) colourScheme.findViewById(R.id.historyList);
	}

	private class ListViewListener implements OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			CustomHistoryListViewAdapter adapter = (CustomHistoryListViewAdapter) listView.getAdapter();
			listView.setItemChecked(position, true);
			db.deleteHistoryItem(adapter.getItem(position));
			adapter.remove((HistoryCalculation) adapter.getItem(position));
			adapter.notifyDataSetInvalidated();
			return false;
		}

	}

	public ArrayList<HistoryCalculation> getColourScheme() {
		ArrayList<HistoryCalculation> schemeList = db.getCalculationList();
		Collections.reverse(schemeList);
		return schemeList;
	}

	private class NavigationListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			Log.v("onClick NavigationListener", "Clicked");
			drawerLayout.openDrawer(Gravity.END);
		}

	}

	private class HistoryListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			CustomHistoryListViewAdapter adapter = (CustomHistoryListViewAdapter) listView.getAdapter();
			for (int i = 0; i < adapter.getCount(); i++) {
				db.deleteHistoryItem(adapter.getItem(i));
			}
			adapter.clear();
			adapter.notifyDataSetInvalidated();
		}
	}
}
