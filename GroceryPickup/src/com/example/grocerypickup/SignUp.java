package com.example.grocerypickup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class SignUp extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_activity);
		
	}
	
	public void register(View view)	{
		
		startActivity(new Intent(getApplicationContext(),Home.class));
		finish();
	}
}
