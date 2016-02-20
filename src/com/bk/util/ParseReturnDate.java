package com.bk.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;

public class ParseReturnDate {

	public String parseLink(JSONObject jObject) {
		String content = "";
		try {
			content = jObject.getString("text");
			String url = jObject.getString("url");

			content = content + ",请点击" + "<a href=" + url + ">" + "打开页面</a>";

			return content;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return content;
	}

	public String parseTrain(JSONObject jObject) {
		String newcontent = "";
		try {

			String tip = jObject.getString("text");
			// System.out.println(tip);

			String content = jObject.getString("list");

			JSONArray jArray = new JSONArray(content);

			String icon = "";// 图片地址
			for (int i = 0; i < jArray.length(); i++) {

				jObject = new JSONObject(jArray.get(i).toString());

				String trainnum = jObject.getString("trainnum");
				String start = jObject.getString("start");
				String terminal = jObject.getString("terminal");
				String starttime = jObject.getString("starttime");
				String endtime = jObject.getString("endtime");
				String detailurl = jObject.getString("detailurl");
				icon = jObject.getString("icon");

				newcontent += 1+i+",<a href=" + detailurl + ">" + trainnum
						+ "</a><br>" + start + "-" + terminal + "<br>"
						+ starttime + "--" + endtime + "<br><br>";
			}

			return newcontent = "<img src='"+icon+"'/><br>"+newcontent;

		} catch (Exception e) {
		}

		return newcontent;
	}

	public String parseFlight(JSONObject jObject) {
		String content = "";
		try {
			content = jObject.getString("text");
			String url = jObject.getString("url");

			content = content + ",请点击" + "<a href=" + url + ">" + "打开页面</a>";

			return content;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return content;
	}

	public String parseMenu(JSONObject jObject) {
		String newcontent = "";
		try {

			String tip = jObject.getString("text");
			// System.out.println(tip);

			String content = jObject.getString("list");

			JSONArray jArray = new JSONArray(content);

			String icon = "";// 图片地址
			for (int i = 0; i < jArray.length(); i++) {

				jObject = new JSONObject(jArray.get(i).toString());

				String name = jObject.getString("name");
				String info = jObject.getString("info");
				String detailurl = jObject.getString("detailurl");
				icon = jObject.getString("icon");
				newcontent += i+1+",<a href=" + detailurl + ">" + name + "</a><br>"
						+ info + "<br>" + "<img src=\""+icon+"\"/><br><br>";
			}

			return newcontent;

		} catch (Exception e) {
		}

		return newcontent;
	}

	public String parseText(JSONObject jObject) {
		String content = "";
		try {
			content = jObject.getString("text");
			return content ;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;
	}

	public String parseNews(JSONObject jObject) {

		String newcontent = "";
		try {

			String tip = jObject.getString("text");
			// System.out.println(tip);

			String content = jObject.getString("list");

			JSONArray jArray = new JSONArray(content);

			String icon = "";// 图片地址
			for (int i = 0; i < jArray.length(); i++) {

				jObject = new JSONObject(jArray.get(i).toString());

				String article = jObject.getString("article");
				String source = jObject.getString("source");
				String detailurl = jObject.getString("detailurl");
				icon = jObject.getString("icon");

				newcontent += 1+i+",<a href=" + detailurl + ">" + article
						+ "</a><br>" + "  ---" +source + "<br><br>";
			}

			return newcontent ;

		} catch (Exception e) {
		}

		return newcontent;

	}
}
