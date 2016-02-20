package com.bk.netdel;

import android.text.Spanned;

public class Ldata {
	
	protected final static int SEND=1;
	protected final static int RECEIVER=2;
	private Spanned content;
	private String time;
	private int flag;
	
	
	public Ldata(Spanned spanned,String time,int flag) {
		this.content=spanned;
		this.time=time;
		this.flag=flag;
	}
	
	public void setContent(Spanned content) {
		this.content = content;
	}
	public Spanned getContent() {
		return content;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
	}

}
