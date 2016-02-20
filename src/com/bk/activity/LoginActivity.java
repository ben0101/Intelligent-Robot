package com.bk.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.bk.dao.UserDao;
import com.bk.util.DatabaseHelper;
import com.serven.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button bnRegister;
	private Button bnLogin;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		
		ActionBar actionBar = getActionBar();  
	    actionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_CUSTOM); 
		
		
		bnRegister=(Button)findViewById(R.id.bnRegister);
		bnLogin=(Button)findViewById(R.id.bnLogin);
		bnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DatabaseHelper dbhepler=new DatabaseHelper(LoginActivity.this);  
			    dbhepler.getReadableDatabase();
			    
			    UserDao uService=new UserDao(LoginActivity.this);  
				       String username=((EditText)findViewById(R.id.userNameText)).getText().toString();  
				       String password=((EditText)findViewById(R.id.passwdText)).getText().toString(); 
				       boolean flag=uService.login(username, password);  
				        if(flag){  
				        	//验证成功，后面加入跳转Activity
				        	//接入
				        	Intent intent=new Intent(LoginActivity.this,MainActivity.class);
				        	startActivity(intent);
				        	finish();
				        	
				        }else{  
				        	Toast.makeText(LoginActivity.this, "登陆失败，用户名或密码错误", Toast.LENGTH_SHORT).show();
				        }
			}
		});
		bnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(registerIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
