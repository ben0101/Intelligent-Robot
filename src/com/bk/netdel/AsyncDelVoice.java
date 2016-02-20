package com.bk.netdel;

import com.qq.wx.voice.recognizer.VoiceRecognizer;
import com.qq.wx.voice.recognizer.VoiceRecognizerListener;
import com.qq.wx.voice.recognizer.VoiceRecognizerResult;
import com.qq.wx.voice.recognizer.VoiceRecordState;

import android.os.AsyncTask;

public class AsyncDelVoice extends AsyncTask<String,Void,String> implements VoiceRecognizerListener{

	@Override
	protected String doInBackground(String... params) {

		
		VoiceRecognizer.shareInstance().start();
		
		
		
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	public void onGetError(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetResult(VoiceRecognizerResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGetVoiceRecordState(VoiceRecordState arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onVolumeChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

}
