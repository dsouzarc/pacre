package com.ryan.recap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.ryan.recap.R;
import android.widget.EditText;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;

public class CreateAccountActivity extends Activity {

    private Context theC;
    private SharedPreferences thePrefs;
    private SharedPreferences.Editor theEditor;
    private EditText nameET, usernameET, passwordET, confirmPasswordET;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        this.theC = this;
        this.thePrefs = this.theC.getSharedPreferences(Constants.APP_TAG, Context.MODE_PRIVATE);
        this.theEditor = this.thePrefs.edit();

        this.nameET = (EditText) findViewById(R.id.nameET);
        this.usernameET = (EditText) findViewById(R.id.emailET);
        this.passwordET = (EditText) findViewById(R.id.passwordET);
        this.confirmPasswordET = (EditText) findViewById(R.id.confirmPasswordET);
        this.createAccountButton = (Button) findViewById(R.id.createAccountButton);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_account, menu);
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
