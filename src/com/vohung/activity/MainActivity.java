package com.vohung.activity;

import java.util.ArrayList;
import java.util.List;

import com.vohung.adapter.CustomDrawerAdapter;
import com.vohung.database.UserDatabase;
import com.vohung.fragment.FragmentChatVL;
import com.vohung.fragment.FragmentShow;
import com.vohung.model.DrawerItem;
import com.vohung.model.User;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private User user = null;
	private Bundle savedInstanceState;
	private UserDatabase userDatabase;
	CustomDrawerAdapter adapter;

	List<DrawerItem> dataList;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.savedInstanceState = savedInstanceState;
		loadActivity();

	}

	private void loadActivity() {
		userDatabase = new UserDatabase(getApplicationContext());
		userDatabase.open();
		user = userDatabase.getUser();
		userDatabase.close();
		dataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		dataList.add(new DrawerItem("ChatVL", R.drawable.ic_action_good));
		dataList.add(new DrawerItem(user, true)); // adding a spinner to the
													// list
		dataList.add(new DrawerItem("MyPosts", R.drawable.ic_action_group));
		dataList.add(new DrawerItem("New", R.drawable.ic_action_email, true));
		dataList.add(new DrawerItem("Hot", R.drawable.ic_action_good, true));
		dataList.add(new DrawerItem("Best", R.drawable.ic_action_gamepad, true));
		dataList.add(new DrawerItem(true));
		dataList.add(new DrawerItem("Help", R.drawable.ic_action_help));
		dataList.add(new DrawerItem("Logout", R.drawable.ic_action_about));

		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
				dataList);

		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {

			if (dataList.get(0).isUser() & dataList.get(1).getTitle() != null) {
				SelectItem(2);
			} else if (dataList.get(0).getTitle() != null) {
				SelectItem(1);
			} else {
				SelectItem(0);
			}
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		loadActivity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void SelectItem(int possition) {

		Fragment fragment = null;
		Bundle args = new Bundle();
		switch (possition) {

		case 0:
			fragment = new FragmentChatVL(getApplicationContext());
			break;

		case 1:
			if (user == null) {
				Intent intent = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(intent);
			}
			break;

		case 3:
			fragment = new FragmentShow(
					getApplicationContext(),
					"http://hoibi.net/new?"
							+ adapter.getUrl().trim()
							+ "_cl_rest=1&_cl_submit=1&_cl_ajax=1&_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c&_cl_platform=1&_cl_ts=1432865516&_cl_deviceID=android&_cl_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c",
					adapter.getUrl_image().trim());
			break;
		case 4:
			fragment = new FragmentShow(
					getApplicationContext(),
					"http://hoibi.net/hot?"
							+ adapter.getUrl().trim()
							+ "_cl_rest=1&_cl_submit=1&_cl_ajax=1&_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c&_cl_platform=1&_cl_ts=1432865516&_cl_deviceID=android&_cl_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c",
					adapter.getUrl_image().trim());
			break;
		case 5:
			fragment = new FragmentShow(
					getApplicationContext(),
					"http://hoibi.net/best?"
							+ adapter.getUrl().trim()
							+ "_cl_rest=1&_cl_submit=1&_cl_ajax=1&_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c&_cl_platform=1&_cl_ts=1432865516&_cl_deviceID=android&_cl_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c",
					adapter.getUrl_image().trim());
			break;
		case 7:
			// fragment = new FragmentTwo();
			// args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
			// .getItemName());
			// args.putInt(FragmentTwo.IMAGE_RESOURCE_ID,
			// dataList.get(possition)
			// .getImgResID());
			break;
		case 8:
			if (user != null) {
				userDatabase.open();
				userDatabase.deleteUser();
				userDatabase.close();
			}
			loadActivity();
			break;

		default:
			break;
		}

		if (fragment != null) {

			fragment.setArguments(args);
			FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();

			mDrawerList.setItemChecked(possition, true);
			setTitle(dataList.get(possition).getItemName());
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return false;
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (dataList.get(position).getTitle() == null) {
				SelectItem(position);
			}

		}
	}

}
