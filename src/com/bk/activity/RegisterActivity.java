package com.bk.activity;

import com.bk.dao.UserDao;
import com.bk.model.User;
import com.bk.util.DatabaseHelper;
import com.serven.R;

import android.R.string;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private Button zhuce;
	private EditText nameEditText;
	private EditText pwdEditText;
	private EditText pwd2EditText;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		
		
		ActionBar actionBar = getActionBar();  
	    actionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_CUSTOM); 
		
		
		zhuce=(Button)findViewById(R.id.zhuce);
		zhuce.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String name=((EditText)findViewById(R.id.name)).getText().toString();
				String pwd=((EditText)findViewById(R.id.pwd)).getText().toString();
				String pwd2=((EditText)findViewById(R.id.pwd2)).getText().toString();
				DatabaseHelper dbhepler=new DatabaseHelper(RegisterActivity.this);  
			    dbhepler.getReadableDatabase(); 
			     if (name.equals("")) {
			    	Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
				}
			    else if(pwd.equals("")||pwd2.equals("")){
			    	Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
			    }
			    else if(!pwd.equals(pwd2)){
			    	Toast.makeText(RegisterActivity.this, "错误，两次密码不一致", Toast.LENGTH_LONG).show();
			    }
			    else{
			    UserDao uService=new UserDao(RegisterActivity.this);  
			         User user=new User();  
			          user.setUsername(name);  
			           user.setPassword(pwd2);  
			          uService.register(user);  
			          Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
			          Thread thread=new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							RegisterActivity.this.finish();
						}
					});
			          try {
						thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			          thread.start();
			    }
			}
		});
	}
}
