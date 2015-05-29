package com.vohung.activity;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

//import com.facebook.UiLifecycleHelper;
//
//import com.facebook.Request;
//import com.facebook.Response;
//import com.facebook.Session;
//import com.facebook.SessionState;
//import com.facebook.UiLifecycleHelper;
//import com.facebook.model.GraphUser;
//import com.facebook.widget.LoginButton;
import com.vohung.database.UserDatabase;
import com.vohung.model.User;
import com.vohung.utils.MyJsonReader;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	User user;
	EditText txtUsername, txtPassWord;
	Button btnLogin, btnSignUp;
	String url;
	UserDatabase userDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		txtUsername = (EditText) findViewById(R.id.editUsername);
		txtPassWord = (EditText) findViewById(R.id.editPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnSignUp = (Button) findViewById(R.id.btnSignUp);

		addEvent();

	}

	private void addEvent() {
		btnLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				url = new String();
				url = "http://hoibi.net/user/login?lname="
						+ txtUsername.getText().toString().trim()
						+ "&pass="
						+ txtPassWord.getText().toString().trim()
						+ "&remember=1&_cl_rest=1&_cl_submit=1&_cl_ajax=1&_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c&_cl_platform=1&_cl_ts=1430726031&_cl_deviceID=android&_cl_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c";
				MyJsonTask task = new MyJsonTask();
				task.execute(url);
			}
		});
		btnSignUp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this,
						RegisterActivity.class);
				startActivity(intent);
			}
		});

	}

	public class MyJsonTask extends AsyncTask<String, JSONObject, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			String url = params[0];
			JSONObject jsonObj;
			try {
				jsonObj = MyJsonReader.readJsonFromUrl(url);
				publishProgress(jsonObj);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(JSONObject... values) {
			super.onProgressUpdate(values);
			JSONObject jsonObj = values[0];
			try {
				if (jsonObj.has("success")) {
					JSONObject userJson = jsonObj.getJSONObject("result");
					user = new User(userJson.getString("avatar").toString(),
							userJson.getString("id").toString(), userJson
									.getString("iid").toString(), userJson
									.getString("lname").toString(), userJson
									.getString("name").toString(), userJson
									.getString("mail").toString(), userJson
									.getString("status").toString(), userJson
									.getString("uhash").toString(), userJson
									.getString("token").toString());

					userDatabase = new UserDatabase(getApplicationContext());
					userDatabase.open();
					userDatabase.insertUser(user);
					userDatabase.close();

					if (jsonObj.getString("success").toString().equals("true")) {
						finish();
					}
				}

			} catch (JSONException e) {
				Toast.makeText(LoginActivity.this, e.toString(),
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
				txtPassWord.setText("");
				txtUsername.setText("");
			}
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	void showMsg(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT)
				.show();
	}

}
