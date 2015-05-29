package com.vohung.adapter;

import java.util.ArrayList;
import java.util.List;

import com.vohung.activity.R;
import com.vohung.model.DrawerItem;
import com.vohung.model.SpinnerItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

	Context context;
	List<DrawerItem> drawerItemList;
	int layoutResID;
	private boolean[] checksetting = { false, false, false };
	public String url = "type[]=1&type[]=2&type[]=3&";
	public String url_image = "http://s.hoibi.net/1000/";

	public String getUrl_image() {
		return url_image;
	}

	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public CustomDrawerAdapter(Context context, int layoutResourceID,
			List<DrawerItem> listItems) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
		this.layoutResID = layoutResourceID;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		DrawerItemHolder drawerHolder;
		View view = convertView;

		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			drawerHolder = new DrawerItemHolder();

			view = inflater.inflate(layoutResID, parent, false);
			drawerHolder.ItemName = (TextView) view
					.findViewById(R.id.drawer_itemName);
			drawerHolder.nameUser = (TextView) view.findViewById(R.id.nameUser);
			drawerHolder.mailUser = (TextView) view.findViewById(R.id.mailUser);
			drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);
			drawerHolder.imageUser = (ImageView) view
					.findViewById(R.id.imageUser);
			drawerHolder.ic_show = (ImageView) view.findViewById(R.id.showhide);

			drawerHolder.spinner = (Spinner) view
					.findViewById(R.id.drawerSpinner);

			drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);

			drawerHolder.headerLayout = (LinearLayout) view
					.findViewById(R.id.headerLayout);
			drawerHolder.itemLayout = (LinearLayout) view
					.findViewById(R.id.itemLayout);
			drawerHolder.userLayout = (LinearLayout) view
					.findViewById(R.id.userLayout);
			drawerHolder.spinnerLayout = (LinearLayout) view
					.findViewById(R.id.spinnerLayout);

			view.setTag(drawerHolder);

		} else {
			drawerHolder = (DrawerItemHolder) view.getTag();

		}

		final DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

		if (dItem.isUser()) {

			drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.userLayout.setVisibility(LinearLayout.VISIBLE);

			if (dItem.getUser() != null) {
				drawerHolder.nameUser.setText(dItem.getUser().getName());
				drawerHolder.mailUser.setText(dItem.getUser().getMail());
			}

		} else if (dItem.isSpinner()) {

			drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.userLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.spinnerLayout.setVisibility(LinearLayout.VISIBLE);

			List<SpinnerItem> settingsList = new ArrayList<SpinnerItem>();

			settingsList.add(new SpinnerItem(R.drawable.ic_action_settings,
					"HD"));
			settingsList.add(new SpinnerItem(R.drawable.ic_action_settings,
					"500"));
			settingsList.add(new SpinnerItem(R.drawable.ic_action_settings,
					"170"));

			CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(context,
					R.layout.custom_spinner_item, settingsList);

			drawerHolder.spinner.setAdapter(adapter);

			drawerHolder.spinner
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							switch (position) {
							case 0:
								url_image = "http://s.hoibi.net/1000/";
								break;
							case 1:
								url_image = "http://s.hoibi.net/500/";
								break;
							case 2:
								url_image = "http://s.hoibi.net/170/";
								break;

							default:
								break;
							}

						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub

						}

					});

		} else if (dItem.getTitle() != null) {
			drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.userLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.title.setText(dItem.getTitle());
			Log.d("Getview", "Passed4");
		} else {

			drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.userLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);

			drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
					dItem.getImgResID()));
			drawerHolder.ItemName.setText(dItem.getItemName());
			Log.d("Getview", "Passed5");
			if (dItem.isShowHide()) {
				drawerHolder.ic_show.setVisibility(ImageView.VISIBLE);
				drawerHolder.ic_show.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						ProgressDialog progressDialog = new ProgressDialog(
								context);
						CharSequence[] items = { "Story", "Image", "Video" };
						checksetting[0] = checksetting[1] = checksetting[2] = false;
						AlertDialog.Builder builder = new AlertDialog.Builder(
								context);
						builder.setTitle("Change Settings");

						builder.setMultiChoiceItems(
								items,
								null,
								new DialogInterface.OnMultiChoiceClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which, boolean isChecked) {
										checksetting[which] = isChecked;

									}
								});
						builder.setNeutralButton("Oke",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										if (checksetting[0] == false
												&& checksetting[1] == false
												&& checksetting[2] == false) {
											checksetting[0] = checksetting[1] = checksetting[2] = true;
										}
										url = "";
										for (int i = 0; i < 3; i++) {
											if (checksetting[i])
												url += "type[]="
														+ Integer
																.toString(i + 1)
														+ "&";
										}

									}
								});
						AlertDialog alert = builder.create();
						alert.show();

					}
				});

			} else {
				drawerHolder.ic_show.setVisibility(ImageView.INVISIBLE);
			}
		}
		return view;
	}

	private static class DrawerItemHolder {
		TextView ItemName, title, nameUser, mailUser;
		ImageView icon, imageUser, ic_show;
		LinearLayout headerLayout, itemLayout, userLayout, spinnerLayout;
		Spinner spinner;
	}

}