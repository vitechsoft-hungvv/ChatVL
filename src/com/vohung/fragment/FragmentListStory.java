package com.vohung.fragment;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.vohung.activity.R;
import com.vohung.model.Story;
import com.vohung.utils.MyJsonReader;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class FragmentListStory extends Fragment {
	ListView listView;
	ArrayList<String> arrList = null;
	ArrayAdapter<String> adapter = null;
	private ArrayList<Story> storyList;
	private int width, height;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = view = inflater.inflate(R.layout.fragment_layout_liststory,
				container, false);
		listView = (ListView) view.findViewById(R.id.idListStory);
		MyJsonStoryAT storyAT = new MyJsonStoryAT();
		storyAT.execute("http://hoibi.net/user/200314?&_cl_rest=1&_cl_submit=1&_cl_ajax=1&_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c&_cl_platform=1&_cl_ts=1431402654&_cl_deviceID=android&_cl_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c");
		arrList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(getActivity()
				.getApplicationContext(), android.R.layout.simple_list_item_1,
				arrList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// Toast.makeText(ListStoryActivity.this,
				// storyList.get(arg2).getIid().toString(),
				// Toast.LENGTH_LONG).show();

			}
		});
		return view;
	}

	public class MyJsonStoryAT extends AsyncTask<String, JSONObject, Void> {

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
				// if (jsonObj.has("cover")) {
				// JSONObject objCover = jsonObj.getJSONObject("cover");
				// if (objCover.has("source")) {
				// urlImage = objCover.getString("source");
				// }
				// }
				publishProgress(jsonObj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
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
					JSONArray listResult = jsonObj.getJSONArray("result");
					storyList = new ArrayList<Story>();
					for (int i = 0; i < listResult.length(); i++) {
						JSONObject storyOJ = listResult.getJSONObject(i);
						// JSONObject tmp = storyOJ.getJSONObject("counter");
						//
						// CounterStory counterStory = new CounterStory(tmp
						// .getString("vote").toString(), tmp.getString(
						// "pint").toString(), tmp.getString("c")
						// .toString(), tmp.getString("fbc").toString(),
						// tmp.getString("tc").toString(), tmp.getString(
						// "vd").toString(), tmp.getString(
						// "p1_vote").toString(), tmp.getString(
						// "p2_vote").toString());
						//
						// storyList.add(new Story(storyOJ.getString("name")
						// .toString(), storyOJ.getString("content")
						// .toString(), storyOJ.getString("type")
						// .toString(), storyOJ.getString("seed_id")
						// .toString(),
						// storyOJ.getString("ts").toString(), storyOJ
						// .getString("iid").toString(), storyOJ
						// .getString("ats").toString(), storyOJ
						// .getString("slug").toString(),
						// counterStory, storyOJ.getString("is_iphone")
						// .toString()));
						arrList.add(storyOJ.getString("name").toString());
						adapter.notifyDataSetChanged();

					}

				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

	}

}
