package com.vohung.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

	static final String DATABASE_CREATE = "CREATE TABLE USERLOGIN ( USER_ID INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,AVATAR TEXT ,ID TEXT ,IID TEXT ,LNAME TEXT,NAME TEXT ,MAIL TEXT  ,STATUS TEXT,UHASH TEXT ,TOKEN TEXT)";

	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");

		onCreate(db);

	}

}
