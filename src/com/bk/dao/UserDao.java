package com.bk.dao;

import com.bk.model.User;
import com.bk.util.DatabaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	 private DatabaseHelper dbHelper;  
	 public UserDao(Context context){  
	     dbHelper=new DatabaseHelper(context);  
	   }  
	      
	     //µÇÂ¼ÓÃ   
	    public boolean login(String username,String password){  
	       SQLiteDatabase sdb=dbHelper.getReadableDatabase();  
	        String sql="select * from user where username=? and password=?";  
	        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});         
	       if(cursor.moveToFirst()==true){  
	            cursor.close();  
	            return true;  
	       }  
	         return false;  
	    }  
	     //×¢²áÓÃ   
	    public boolean register(User user){  
	        SQLiteDatabase sdb=dbHelper.getReadableDatabase();  
	        String sql="insert into user(username,password) values(?,?)";  
	         Object obj[]={user.getUsername(),user.getPassword()};  
	        sdb.execSQL(sql, obj);    
	         return true;  
	    }  

}
