package com.kiiolabs.cally.view;

import java.util.ArrayList;

import com.kiiolabs.cally.R;
import com.kiiolabs.cally.adapter.NavigationListAdapter;
import com.kiiolabs.cally.model.Utility;
import com.kiiolabs.cally.model.bean.HistoryCalculation;
import com.kiiolabs.cally.model.bean.NavigationItem;
import com.kiiolabs.cally.view.Fragments.AboutFragment;
import com.kiiolabs.cally.view.Fragments.ColourSchemeFragment;
import com.kiiolabs.cally.view.Fragments.HistoryFragment;
import com.kiiolabs.cally.view.Fragments.RegularFragment;
import com.kiiolabs.cally.view.Fragments.ScientificFragment;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<NavigationItem> navDrawerItems;
	private NavigationListAdapter adapter;
	private Utility utils;
	private int colour;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		utils = new Utility(getApplicationContext());
		colour = utils.getColourGlobal();
		navDrawerItems = new ArrayList<NavigationItem>();

		navDrawerItems.add(new NavigationItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		navDrawerItems.add(new NavigationItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		navDrawerItems.add(new NavigationItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		navDrawerItems.add(new NavigationItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		navDrawerItems.add(new NavigationItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		adapter = new NavigationListAdapter(getApplicationContext(), navDrawerItems);
		mDrawerList.setAdapter(adapter);

		if (savedInstanceState == null) {
			int tempPosition = utils.loadSavedPreferencesForUserChoice();
			displayView(tempPosition);
		}
	}

	/**
	 * Slide menu item click listener
	 */
	private class SlideMenuClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		FragmentManager fragmentManager = this.getSupportFragmentManager();
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			fragmentManager.popBackStack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		Log.v("Colour DisplayItem", String.valueOf(colour));
		String tag = "default";
		switch (position) {
		case 0:
			fragment = RegularFragment.InstanceOf(colour);
			tag = "com.cally.regular";
			utils.savePreferencesForUserChoice(position);
			break;
		case 1:
			fragment = ScientificFragment.InstanceOf(colour);
			tag = "com.cally.scientific";
			utils.savePreferencesForUserChoice(position);
			break;
		case 2:
			FragmentManager dialog = getSupportFragmentManager();
			ColourSchemeFragment dialogFragment = ColourSchemeFragment.InstanceOf();
			dialogFragment.show(dialog, "");
			break;
		case 3:
			fragment = HistoryFragment.InstanceOf(colour);
			tag = "com.cally.history";
			utils.savePreferencesForUserChoice(position);
			break;
		case 4:
			FragmentManager fragmentAboutManager = getSupportFragmentManager();
			AboutFragment aboutFragment = AboutFragment.InstanceOf(colour);
			aboutFragment.show(fragmentAboutManager, "");
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, tag).addToBackStack(tag)
					.commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
		CloseDrawer();
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
	}

	public void CloseDrawer() {
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public DrawerLayout getmDrawerLayout() {
		return mDrawerLayout;
	}

}
