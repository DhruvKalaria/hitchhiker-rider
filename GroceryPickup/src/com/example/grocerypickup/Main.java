package com.example.grocerypickup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends Activity{

	private EditText  username=null;
	private EditText  password=null;
	private TextView attempts;
	private Button login;
	int counter = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		username = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		attempts = (TextView)findViewById(R.id.textView5);
		attempts.setText(Integer.toString(counter));
		login = (Button)findViewById(R.id.button1);
	}

	public void login(View view){
		if(username.getText().toString().equals("admin") && 
				password.getText().toString().equals("admin")){
			Toast.makeText(getApplicationContext(), "Redirecting...", 
					Toast.LENGTH_SHORT).show();			
			startActivity(new Intent(getApplicationContext(),Home.class));
			finish();
			
		}	
		else{
			Toast.makeText(getApplicationContext(), "Wrong Credentials",
					Toast.LENGTH_SHORT).show();
			attempts.setBackgroundColor(Color.RED);	
			counter--;
			attempts.setText(Integer.toString(counter));
			if(counter==0){
				login.setEnabled(false);
			}

		}

	}
	
	public void signUp(View view)	{
		
		startActivity(new Intent(getApplicationContext(),SignUp.class));
		finish();
	}

}