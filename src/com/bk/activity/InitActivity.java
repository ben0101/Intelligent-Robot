package com.bk.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.serven.R;
import com.serven.R.id;
import com.serven.R.layout;
import com.serven.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class InitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.init_layout);
		
		
		
		Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				Intent intent=new Intent(InitActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
				
			}
		};
		timer.schedule(task,4000);
	}
	
	
	

}
