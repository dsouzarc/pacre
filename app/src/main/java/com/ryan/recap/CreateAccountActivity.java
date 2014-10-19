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
import android.view.View;

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

    private final View.OnClickListener createAccountListener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            //Check if name is valid
            final String name = nameET.getText().toString();

            if(name.length() < 3 || !name.contains(" ")) {
                makeToast("Please enter a valid first and last name");
                return;
            }

            final String password = passwordET.getText().toString();
            final String passwordConfirm = confirmPasswordET.getText().toString();

            if(!password.equals(passwordConfirm)) {
                makeToast("Passwords do not match");
                return;
            }

            if(password.length() < 5) {
                makeToast("Please enter a longer password");
                return;
            }

            final String email = usernameET.getText().toString();
            if(email.substring(email.length() - 4).equals(".edu")) {
                makeToast("Please enter a valid .edu email address");
            }
            if(email.substring(email.length() - 13).equals("quinnipac.edu")) {
                makeToast("Please enter a valid quinnipac.edu");
            }
            makeToast("Please check your email to confirm this account");

            theEditor.putString(Constants.NAME, name);
            theEditor.putString(Constants.USERNAME, email);
            theEditor.putString(Constants.PASSWORD, password);
            theEditor.commit();

            startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
        }
    };

    private void makeToast(final String message) {
        Constants.makeToast(theC, message);
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
