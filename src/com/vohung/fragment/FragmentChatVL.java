package com.vohung.fragment;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vohung.activity.LoginActivity;
import com.vohung.activity.R;
import com.vohung.model.ImagesStory;
import com.vohung.model.Story;
import com.vohung.utils.ImageLoader;
import com.vohung.utils.MyJsonReader;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class FragmentChatVL extends Fragment implements OnTouchListener {

	private TextView txtTitleImage;
	private ImageView imageView, nextI, preI;
	private Button videoPlay;
	private ImageButton btnLike;
	private int count = 0;
	private ImageLoader imageLoader;
	private ArrayList<Story> storyList;
	private ImagesStory imagesStory;
	private String url;
	private String url_image = "http://s.hoibi.net/50/";
	private Context context;
	private MyJsonStoryAT storyAT;
	private int width, height;
	int host = 1;
	private View view;

	public FragmentChatVL(Context context) {
		super();
		this.context = context;
	}

	private final GestureDetector detector = new GestureDetector(
			new SwipeGestureDetector());

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout_chatvl1, container,
				false);
		addEvent();
		loadData();
		return view;
	}

	private void loadData() {
		storyList = new ArrayList<Story>();
		count = 0;
		host = 1;
		WindowManager wm = (WindowManager) getActivity()
				.getApplicationContext().getSystemService(
						Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
		imageLoader = new ImageLoader(context);
		storyAT = new MyJsonStoryAT();
		storyAT.execute("http://hoibi.net/hot?&_cl_rest=1&_cl_submit=1&_cl_ajax=1&_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c&_cl_platform=1&_cl_ts=1431135892&_cl_deviceID=android&_cl_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c");

	}

	private void addEvent() {
		txtTitleImage = (TextView) view.findViewById(R.id.txtTitleImage);
		imageView = (ImageView) view.findViewById(R.id.idImageView);
		nextI = (ImageView) view.findViewById(R.id.swipe_next);
		preI = (ImageView) view.findViewById(R.id.swipe_pre);
		videoPlay = (Button) view.findViewById(R.id.playvideo);
		nextI.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextImage();

			}
		});
		preI.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				previousImage();
			}
		});
		btnLike = (ImageButton) view.findViewById(R.id.btnLike);
		btnLike.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity()
						.getApplicationContext(), LoginActivity.class);
				startActivity(intent);
			}
		});
		imageView.setOnTouchListener(this);
	}

	class SwipeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			float ev1X = e1.getX();
			float ev2X = e2.getX();
			final float xdistance = Math.abs(ev1X - ev2X);
			final float xvelocity = Math.abs(velocityX);
			if ((xvelocity > 50) && (xdistance > 50)) {
				if (ev1X > ev2X) {
					nextImage();
				} else {
					previousImage();
				}

			}

			return false;
		}

	}

	private void nextImage() {
		if (count < storyList.size() - 1) {
			UploadData(storyList.get(++count));
		} else {
			count = 0;
			imageLoader.clearCache();
			url = "http://hoibi.net/hot/"
					+ Integer.toString(++host)
					+ "?&_cl_rest=1&_cl_submit=1&_cl_ajax=1&_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c&_cl_platform=1&_cl_ts=1431135892&_cl_deviceID=android&_cl_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c";
			storyAT = new MyJsonStoryAT();
			storyAT.execute(url);

		}
	}

	private void previousImage() {
		if (count > 0)
			UploadData(storyList.get(--count));
		else if (host > 1) {
			count = storyList.size() - 1;
			imageLoader.clearCache();
			url = "http://hoibi.net/hot/"
					+ Integer.toString(--host)
					+ "?&_cl_rest=1&_cl_submit=1&_cl_ajax=1&_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c&_cl_platform=1&_cl_ts=1431067117&_cl_deviceID=android&_cl_app_id=6998c62207fe21515b9d0bcc259fc7a820a1a4bba879c01a622da400c233857c";
			storyAT = new MyJsonStoryAT();
			storyAT.execute(url);
		}
	}

	private void UploadData(Story story) {
		if (story.getYtid() == null) {
			videoPlay.setVisibility(Button.INVISIBLE);
			imageView.setVisibility(ImageView.INVISIBLE);
			if (width > story.getImage().getW()) {
				LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
						story.getImage().getW(), story.getImage().getH());
				imageView.setLayoutParams(param);
			} else {
				LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
						width, height * story.getImage().getH()
								/ story.getImage().getW());
				imageView.setLayoutParams(param);

			}
			imageLoader.DisplayImage(url_image + story.getImage().getUrl(),
					imageView);
			txtTitleImage.setText(story.getName());
			imageView.setVisibility(ImageView.VISIBLE);
		} else {
			final String tmp = story.getYtid();
			videoPlay.setVisibility(Button.VISIBLE);
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
					width, height / 2);
			imageView.setLayoutParams(param);
			videoPlay.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(
							android.content.Intent.ACTION_VIEW);
					Uri data = Uri.parse("https://www.youtube.com/watch?v="
							+ tmp);
					intent.setData(data);
					startActivity(intent);
				}
			});
			imageLoader.DisplayImage(
					"http://img.youtube.com/vi/" + story.getYtid() + "/0.jpg",
					imageView);
			txtTitleImage.setText(story.getName());
		}
	}

	public class MyJsonStoryAT extends AsyncTask<String, String, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			String url = params[0];
			JSONObject jsonObj;
			try {
				jsonObj = MyJsonReader.readJsonFromUrl(url);
				if (jsonObj.has("success")) {
					JSONArray listResult = jsonObj.getJSONArray("result");

					storyList.clear();
					for (int i = 0; i < listResult.length(); i++) {
						JSONObject storyOJ = listResult.getJSONObject(i);
						Story story;
						if (storyOJ.getString("source").toString().trim()
								.equals("Youtube.com")) {
							imagesStory = null;
							story = new Story(storyOJ.getString("name")
									.toString(), storyOJ.getString("iid")
									.toString(), storyOJ.getString("id")
									.toString(), storyOJ.getString("ytid")
									.toString(), imagesStory);

						} else {
							JSONArray tmp1 = storyOJ.getJSONArray("images");
							JSONObject imageJS = tmp1.getJSONObject(0);

							imagesStory = new ImagesStory(imageJS.getString(
									"id").toString(), imageJS.getString("ext")
									.toString(), imageJS.getString("url")
									.toString(), imageJS.getInt("h"),
									imageJS.getInt("w"));
							publishProgress(imagesStory.getUrl());
							story = new Story(storyOJ.getString("name")
									.toString(), storyOJ.getString("iid")
									.toString(), storyOJ.getString("id")
									.toString(), null, imagesStory);
						}
						storyList.add(story);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			imageLoader.DisplayImage(values[0], imageView);
			if (count == 0) {
				UploadData(storyList.get(count));
			}
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		detector.onTouchEvent(event);
		return true;
	}

}
