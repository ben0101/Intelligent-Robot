package com.bk.netdel;

import java.util.List;

import com.serven.R;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter{
	
	private List<Ldata> list;
	private Context context;
	private RelativeLayout layout;
	
	public ListAdapter(List<Ldata> list,Context context){
		this.list=list;
		this.context=context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup vGroup) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater=LayoutInflater.from(context);
		if(list.get(position).getFlag()==1){
			layout=(RelativeLayout) layoutInflater.inflate(R.layout.rightitem,vGroup,false);
		}
		if(list.get(position).getFlag()==2){
			layout=(RelativeLayout) layoutInflater.inflate(R.layout.leftitem,vGroup,false);
		}
		
		TextView tv=(TextView) layout.findViewById(R.id.tv);
		TextView tm=(TextView) layout.findViewById(R.id.time);
		
		//textView能响应用户点击文本中的超链接等
		tv.setMovementMethod(LinkMovementMethod.getInstance());
		//在这设置文字居中，布局中可能因html不起作用
		tv.setGravity(Gravity.CENTER_VERTICAL);
		tv.setTextSize(18);
		tv.setTextColor(Color.BLACK);
		//tv.setTextIsSelectable(true);
		tm.setTextColor(Color.BLACK);
		
		tv.setText(list.get(position).getContent());
		tm.setText(list.get(position).getTime());
		
		return layout;
	}

}
