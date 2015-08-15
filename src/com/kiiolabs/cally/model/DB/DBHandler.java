package com.kiiolabs.cally.model.DB;

import java.util.ArrayList;

import com.kiiolabs.cally.model.bean.HistoryCalculation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
	private final static String DATABASE_NAME = "History_Calculation";
	private static int DB_VERSION = 1;
	private static DBHandler instance;
	private String CALCULATION_TABLE_NAME = "Calculations";
	private String calculation_ID = "calculationID";
	private String calculation_Expression = "calculationExpression";
	private String calculation_Answer = "calculationAnswer";
	private String calculation_Date = "calculationDate";
	private String CREATE_calculation_TB = "CREATE TABLE " + CALCULATION_TABLE_NAME + "( " + calculation_ID
			+ " INTEGER PRIMARY KEY NOT NULL," + calculation_Expression + " VARCHAR( 100 ) NOT NULL , "
			+ calculation_Answer + " VARCHAR( 100 )," + calculation_Date + " VARCHAR(30) NOT NULL);";

	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_calculation_TB);
		db.setVersion(DB_VERSION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v("Version Update", "Upgrading Database With Version: " + newVersion);
		db.execSQL("DROP TABLE IF EXISTS '" + CALCULATION_TABLE_NAME + "'");
		this.onCreate(db);
	}

	public void addHistoryCalculations(HistoryCalculation historyItem) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(calculation_Expression, historyItem.getExpression());
		values.put(calculation_Answer, historyItem.getAnswer());
		values.put(calculation_Date, historyItem.getTime());
		int idTemp = (int) db.insert(CALCULATION_TABLE_NAME, // table
				null, // nullColumnHack
				values); // key/value -> keys = column names/ values = column
		Log.v("AddHistoryCalculation", "Added:" + historyItem.getAnswer());
	}

	public ArrayList<HistoryCalculation> getCalculationList() {
		// TODO Auto-generated method stub
		ArrayList<HistoryCalculation> calculationList = new ArrayList<HistoryCalculation>();
		String query = "SELECT * FROM " + CALCULATION_TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				int calculationId = Integer.parseInt(cursor.getString(0));
				String calculationHistory = cursor.getString(1);
				String calculationAnswer = cursor.getString(2);
				String calculationTime = cursor.getString(3);
				calculationList.add(
						new HistoryCalculation(calculationId, calculationHistory, calculationAnswer, calculationTime));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return calculationList;
	}

	public void deleteHistoryItem(HistoryCalculation calculationHistoryItem) {
		SQLiteDatabase db = this.getWritableDatabase();
		Log.v("Completed Challenge", calculationHistoryItem.toString());
		String query = "DELETE FROM " + CALCULATION_TABLE_NAME + " WHERE  " + calculation_ID + " = '"
				+ calculationHistoryItem.getId() + "'";
		db.execSQL(query);
		Log.v("Deleted Complated Challenge", "Deleted");
	}

}
