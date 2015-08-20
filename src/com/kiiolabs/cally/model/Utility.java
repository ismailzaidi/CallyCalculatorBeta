package com.kiiolabs.cally.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.kiiolabs.cally.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;

public class Utility {
	private String Color_Scheme_Key = "com.kiiolabs.cally.colourscheme";
	private String Fragment_choice_Key = "com.kiiolabs.cally.userchoice";
	private Context context;
	public Utility(Context context){
		this.context = context;
	}
	public int loadSavedPreferencesForColourScheme() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		int name = sharedPreferences.getInt(Color_Scheme_Key, 0);
		return name;
	}

	public void savePreferencesForColourScheme(int position) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putInt(Color_Scheme_Key, position);
		editor.commit();
	}
	public int loadSavedPreferencesForUserChoice() {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		int name = sharedPreferences.getInt(Fragment_choice_Key, 0);
		return name;
	}

	public void savePreferencesForUserChoice(int position) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putInt(Fragment_choice_Key, position);
		editor.commit();
	}
	public String getCurrentTime(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		return sdf.format(cal.getTime());
		
	}
	public int getColourGlobal(){
		int colour_position = loadSavedPreferencesForColourScheme();
		TypedArray colourArray = context.getResources().obtainTypedArray(R.array.colorscheme_icons);
		int colour = colourArray.getResourceId(colour_position, 0);
		return colour;
	}
}
