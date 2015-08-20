package com.kiiolabs.cally.view.Fragments;

import java.util.ArrayList;

import com.kiiolabs.cally.R;
import com.kiiolabs.cally.adapter.CustomColourListViewAdapter;
import com.kiiolabs.cally.model.Utility;
import com.kiiolabs.cally.model.bean.ColourScheme;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ColourSchemeFragment extends DialogFragment {
	private Context context;
	private CustomColourListViewAdapter adapter;
	private String[] titles;
	private ListView listView;
	private RelativeLayout titleLayout;
	private TypedArray navMenuIcons, navMenuTickIcons, navMenuColourScheme;
	private Utility utils;
	public static ColourSchemeFragment InstanceOf() {
		ColourSchemeFragment fragment = new ColourSchemeFragment();
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
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		View colourScheme = inflater.inflate(com.kiiolabs.cally.R.layout.colour_dialog_fragment, container, false);
		context = getActivity().getApplicationContext();
		utils = new Utility(context);
		int colour_position = utils.loadSavedPreferencesForColourScheme();

		titleLayout = (RelativeLayout) colourScheme.findViewById(R.id.navigation);
		listView = (ListView) colourScheme.findViewById(R.id.colourSchemeList);
		adapter = new CustomColourListViewAdapter(getActivity(), getColourScheme(), colour_position);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new ListViewListener());
		titleLayout.setBackgroundColor(getResources().getColor(navMenuColourScheme.getResourceId(colour_position, -1)));
		return colourScheme;
	}

	private class ListViewListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			CustomColourListViewAdapter adapter = (CustomColourListViewAdapter) listView.getAdapter();
			listView.setItemChecked(position, true);
			int color = adapter.getItem(position).getColor();
			ImageView tick_image = (ImageView) view.findViewById(R.id.tick);
			tick_image.setVisibility(View.VISIBLE);
			utils.savePreferencesForColourScheme(position);
			FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
			String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getName();
			Fragment fragment = null;
			if(fragmentTag.equals("com.cally.regular")){
				fragment = RegularFragment.InstanceOf(color);
			} else if(fragmentTag.equals("com.cally.scientific")){
				fragment = ScientificFragment.InstanceOf(color);
			}else{
				fragment = HistoryFragment.InstanceOf(color);
			}
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
			getDialog().dismiss();
		}

	}
	public ArrayList<ColourScheme> getColourScheme() {
		ArrayList<ColourScheme> schemeList = new ArrayList<ColourScheme>();
		titles = context.getResources().getStringArray(R.array.scheme_items);
		navMenuIcons = context.getResources().obtainTypedArray(R.array.scheme_icons);
		navMenuTickIcons = context.getResources().obtainTypedArray(R.array.scheme_ticks_icons);
		navMenuColourScheme = context.getResources().obtainTypedArray(R.array.colorscheme_icons);
		schemeList.add(new ColourScheme(titles[0], navMenuIcons.getResourceId(0, -1),
				navMenuTickIcons.getResourceId(0, -1), navMenuColourScheme.getResourceId(0, -1)));
		schemeList.add(new ColourScheme(titles[1], navMenuIcons.getResourceId(1, -1),
				navMenuTickIcons.getResourceId(1, -1), navMenuColourScheme.getResourceId(1, -1)));
		schemeList.add(new ColourScheme(titles[2], navMenuIcons.getResourceId(2, -1),
				navMenuTickIcons.getResourceId(2, -1), navMenuColourScheme.getResourceId(2, -1)));
		schemeList.add(new ColourScheme(titles[3], navMenuIcons.getResourceId(3, -1),
				navMenuTickIcons.getResourceId(3, -1), navMenuColourScheme.getResourceId(3, -1)));
		schemeList.add(new ColourScheme(titles[4], navMenuIcons.getResourceId(4, -1),
				navMenuTickIcons.getResourceId(4, -1), navMenuColourScheme.getResourceId(4, -1)));
		schemeList.add(new ColourScheme(titles[5], navMenuIcons.getResourceId(5, -1),
				navMenuTickIcons.getResourceId(5, -1), navMenuColourScheme.getResourceId(5, -1)));
		schemeList.add(new ColourScheme(titles[6], navMenuIcons.getResourceId(6, -1),
				navMenuTickIcons.getResourceId(6, -1), navMenuColourScheme.getResourceId(6, -1)));
		schemeList.add(new ColourScheme(titles[7], navMenuIcons.getResourceId(7, -1),
				navMenuTickIcons.getResourceId(7, -1), navMenuColourScheme.getResourceId(7, -1)));
		schemeList.add(new ColourScheme(titles[8], navMenuIcons.getResourceId(8, -1),
				navMenuTickIcons.getResourceId(8, -1), navMenuColourScheme.getResourceId(8, -1)));
		schemeList.add(new ColourScheme(titles[9], navMenuIcons.getResourceId(9, -1),
				navMenuTickIcons.getResourceId(9, -1), navMenuColourScheme.getResourceId(9, -1)));
		schemeList.add(new ColourScheme(titles[10], navMenuIcons.getResourceId(10, -1),
				navMenuTickIcons.getResourceId(10, -1), navMenuColourScheme.getResourceId(10, -1)));
		return schemeList;
	}
}
