package com.bk.activity;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bk.netdel.AsyncResponse;
import com.bk.netdel.Httpdatadel;
import com.bk.netdel.Ldata;
import com.bk.netdel.ListAdapter;
import com.bk.util.ParseReturnDate;
import com.bk.util.UiUtil;
import com.qq.wx.voice.recognizer.VoiceRecognizer;
import com.qq.wx.voice.recognizer.VoiceRecognizerListener;
import com.qq.wx.voice.recognizer.VoiceRecognizerResult;
import com.qq.wx.voice.recognizer.VoiceRecordState;
import com.qq.wx.voice.recognizer.VoiceRecognizerResult.Word;
import com.serven.R;
import com.serven.R.id;
import com.serven.R.layout;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,
		AsyncResponse, VoiceRecognizerListener {

	private ListView lv;
	private List<Ldata> list;
	private ListAdapter adapterc;
	private Ldata ldata;
	private Button btn_send, btn_voice;
	private EditText edit_content;
	private Httpdatadel httpdatadel;
	private double currentTime;
	private double oldTime;
	private String mRecognizerResult;
	private Spanned sp;
	private ParseReturnDate parseRD;

	private Handler handler;

	private final static String screKey = "248b63f1ceca9158ca88516bcb338e82a482ecd802cbca12";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	private void init() {
		
		
	    ActionBar actionBar = getActionBar();  
	    actionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_CUSTOM); 
		
		
		
		// listview适配
		lv = (ListView) findViewById(R.id.lv);
		list = new ArrayList<Ldata>();
		adapterc = new ListAdapter(list, this);
		lv.setAdapter(adapterc);

		// 初始化时显示的数据
		ldata = new Ldata(Html.fromHtml("hello"), getTime(), 2);
		list.add(ldata);

		// 发送事件
		btn_send = (Button) findViewById(R.id.btn_send);
		btn_voice = (Button) findViewById(R.id.btn_voice);
		btn_send.setOnClickListener(this);
		btn_voice.setOnClickListener(this);

		edit_content = (EditText) findViewById(R.id.etext);

		new Thread(new Runnable() {

			@Override
			public void run() {
				Looper.prepare();// 非主线程中默认没有创建Looper对象，
				// Message msg = handler.obtainMessage();
				//
				// handler.sendMessage(msg);

				// 录音器监听
				VoiceRecognizer.shareInstance().setSilentTime(1000);
				VoiceRecognizer.shareInstance().setListener(
						(VoiceRecognizerListener) MainActivity.this);
				if (VoiceRecognizer.shareInstance().init(MainActivity.this,
						screKey) != 0) {
					Toast.makeText(MainActivity.this, "failed to init", 0)
							.show();
				}

				Looper.loop();

			}

		}).start();

	}

	/*
	 * 发送
	 */
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_send:

			// 得到输入内容，并作处理
			String edit_contentStr = edit_content.getText().toString();
			String contentStr = edit_contentStr.replace("\n", "");
			contentStr = contentStr.replace(" ", "");
			try {
				// ??contentStr=new String(contentStr.getBytes("gbk"),"UTF-8");
				contentStr = URLEncoder.encode(contentStr, "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}

			// 删除页面的一些数据
			UiUtil.removelists(list.size(), list);

			// 更新页面
			edit_content.setText("");
			ldata = new Ldata(Html.fromHtml(edit_contentStr), getTime(), 1);
			list.add(ldata);
			adapterc.notifyDataSetChanged();

			httpdatadel = (Httpdatadel) new Httpdatadel(
					"http://www.tuling123.com/openapi/api?key=在tuling123.com上注册个号便有自己的key了&info="
							+ contentStr, this).execute();
			break;

		case R.id.btn_voice:
			VoiceRecognizer.shareInstance().start();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String contentStr1 = mRecognizerResult;

			if (contentStr1 != null) {
				try {
					// ??contentStr=new
					// String(contentStr.getBytes("gbk"),"UTF-8");
					contentStr1 = URLEncoder.encode(contentStr1, "UTF-8");
				} catch (UnsupportedEncodingException e) {
				}

			}

			

			httpdatadel = (Httpdatadel) new Httpdatadel(
					"http://www.tuling123.com/openapi/api?key=在tuling123.com上注册个号便有自己的key了&info="
							+ contentStr1, MainActivity.this).execute();

			break;
		}
	}

	/*
	 * 返回
	 */

	@Override
	public void getResponse(String str) {
		if (str != null) {
			parseData(str);
		}

	}

	ImageGetter imageGetter;
	String content = "";

	public void parseData(String data) {
		try {
			// 利用JSONObject 得到text内容，具体可到图灵机器人参考
			JSONObject jObject = new JSONObject(data);
			int key = Integer.parseInt(jObject.getString("code"));
			parseRD = new ParseReturnDate();

			switch (key) {
			case 100000:// text
				content = parseRD.parseText(jObject);
				break;
			case 200000:// link
				content = parseRD.parseLink(jObject);
				break;
			case 302000:// news
				content = parseRD.parseNews(jObject);
				break;
			case 305000:// train
				content = parseRD.parseTrain(jObject);
				break;
			case 306000:// fight
				content = parseRD.parseFlight(jObject);
				break;
			case 308000:// menu
				content = parseRD.parseMenu(jObject);
				break;

			}

			// 有图片地址才加载
			if (content.contains("img")) {

				// 加载网络图片[
				new Thread(new Runnable() {
					@Override
					public void run() {
						imageGetter = new ImageGetter() {
							@Override
							public Drawable getDrawable(String source) {

								InputStream is = null;
								Drawable drawable = null;
								URL url;
								try {
									url = new URL(source);
									is = (InputStream) url.getContent();
									drawable = Drawable.createFromStream(is,
											"src"); // 获取网路图片
									drawable.setBounds(0, 0,
											drawable.getIntrinsicWidth(),
											drawable.getIntrinsicHeight());
									is.close();
									return drawable;
								} catch (Exception e) {
									return null;
								}
							};

						};

						sp = Html.fromHtml(content, imageGetter, null);
					}
				}).start();

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				sp = Html.fromHtml(content);
			}

			ldata = new Ldata(sp, getTime(), 2);
			list.add(ldata);
			adapterc.notifyDataSetChanged();

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void isNetworkAvaliable() {
		ConnectivityManager cManager = (ConnectivityManager) this
				.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo nInfo = cManager.getActiveNetworkInfo();
		if (nInfo == null) {
			Toast.makeText(this, "error", 0).show();
			return;
		}
		Toast.makeText(this, "good", 0).show();

	}

	private String getTime() {
		currentTime = System.currentTimeMillis();
		if (currentTime - oldTime > 240000) {
			oldTime = currentTime;
			return UiUtil.formatDate();
		}
		return "";
	}

	/*
	 * 语音
	 */
	@Override
	public void onGetError(int arg0) {

	}

	@Override
	public void onGetResult(VoiceRecognizerResult result) {

		mRecognizerResult = "";
		if (result != null && result.words != null) {
			int wordSize = result.words.size();
			StringBuilder results = new StringBuilder();
			for (int i = 0; i < wordSize; ++i) {
				Word word = (Word) result.words.get(i);
				if (word != null && word.text != null) {
					results.append("\r\n");
					results.append(word.text.replace(" ", ""));
				}
			}
			results.append("\r\n");
			mRecognizerResult = results.toString();
			Toast.makeText(this, mRecognizerResult + "****", 0).show();
		}

	}

	public void onGetVoiceRecordState(VoiceRecordState state) {
	}

	public void onVolumeChanged(int arg0) {
	}

	@Override
	protected void onDestroy() {
		VoiceRecognizer.shareInstance().destroy();
		super.onDestroy();
	}

}
