package com.vohung.database;

import com.vohung.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserDatabase {

	static final String DATABASE_NAME = "db.chatVLdemo";
	static final int DATABASE_VERSION = 1;
	public SQLiteDatabase db;
	private Context context;
	private DataBaseHelper dataBaseHelper;

	public UserDatabase(Context context) {
		super();
		this.context = context;
		dataBaseHelper = new DataBaseHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);

	}

	public UserDatabase open() throws SQLException {
		db = dataBaseHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public SQLiteDatabase getDatabaseInstance() {
		return db;
	}

	public void insertUser(User user) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("AVATAR", user.getAvatar());
		contentValues.put("ID", user.getId());
		contentValues.put("IID", user.getIid());
		contentValues.put("LNAME", user.getLname());
		contentValues.put("NAME", user.getName());
		contentValues.put("MAIL", user.getMail());
		contentValues.put("STATUS", user.getStatus());
		contentValues.put("UHASH", user.getUhash());
		contentValues.put("TOKEN", user.getToken());
		db.insert("USERLOGIN", null, contentValues);
	}

	public User getUser() {
		User user = null;
		Cursor cursor = db.query("USERLOGIN", null, null, null, null, null,
				null);
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			user = new User(cursor.getString(1), cursor.getString(2),
					cursor.getString(3), cursor.getString(4),
					cursor.getString(5), cursor.getString(6),
					cursor.getString(7), cursor.getString(8),
					cursor.getString(9));
			cursor.moveToNext();
		}
		return user;
	}

	public void deleteUser() {
		db.delete("USERLOGIN", null, null);
	}


}
