package com.example.jose.android_mysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	EditText etUsername, etPassword;
	Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		etUsername = (EditText) findViewById(R.id.etUsername);
		etPassword= (EditText) findViewById(R.id.etPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);

		Button btnAddProduct = (Button)findViewById(R.id.btnAddProduct);
		btnAddProduct.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, Main2Activity.class));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		String username = etUsername.getText().toString();
		String password = etPassword.getText().toString();
		HashMap postData = new HashMap();
		postData.put("txtUsername", username);
		postData.put("txtPassword", password);

		PostResponseAsyncTask task = new PostResponseAsyncTask(MainActivity.this, postData, new AsyncResponse() {
			@Override
			public void processFinish(String result) {
				if(result.contains("success")){
					Intent in = new Intent(MainActivity.this, SubActivity.class);
					startActivity(in);
				}
				else {
					Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_LONG).show();

				}

			}
		});

		task.execute("http://10.0.3.2:8080/customer/index.php");

	}
}
