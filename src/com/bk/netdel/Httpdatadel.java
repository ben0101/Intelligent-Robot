package com.bk.netdel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Httpdatadel extends AsyncTask<String,Void,String> {

	private String url;
	private AsyncResponse aResponse;
	private BufferedReader bReader;
	private StringBuilder sBuilder;
	
	public Httpdatadel(String url,AsyncResponse aResponse){
		this.url=url;
		this.aResponse=aResponse;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		try {
			//发送请求数据并处理返回的数据，要配置网络权限
			URL getUrl = new URL(url);
		    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		    connection.setConnectTimeout(5000);
		    connection.connect();
		    bReader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			
		    
			String line="";
			sBuilder=new StringBuilder();
			while((line=bReader.readLine())!=null){
				sBuilder.append(line);
			}
			bReader.close();
			connection.disconnect();
			
			return sBuilder.toString();
			
		} catch (Exception e) {}
		
		return null;
	}
	
	//doInBackground处理完后得到返回结果,用接口传递
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		//if(result!=null){
			aResponse.getResponse(result);
		//}
		super.onPostExecute(result);
	}

}
