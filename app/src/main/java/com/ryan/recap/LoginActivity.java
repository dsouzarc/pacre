package com.ryan.recap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;

public class LoginActivity extends Activity {

    private Context theC;
    private SharedPreferences thePrefs;
    private SharedPreferences.Editor theEditor;

    private EditText emailAddress, password;
    private Button loginButton;
    private ImageView pictureIV;
    private TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.theC = this;
        this.thePrefs = theC.getSharedPreferences(Constants.APP_TAG, Context.MODE_PRIVATE);
        this.theEditor = this.thePrefs.edit();

        this.pictureIV = (ImageView) findViewById(R.id.imageView);
        this.emailAddress = (EditText) findViewById(R.id.emailAddressET);
        this.password = (EditText) findViewById(R.id.passwordET);
        this.loginButton = (Button) findViewById(R.id.loginButton);
        this.createAccount = (TextView) findViewById(R.id.createAccount);

        this.emailAddress.setText(getPreference(Constants.USERNAME));
        this.password.setText(getPreference(Constants.PASSWORD));

        this.createAccount.setOnClickListener(createAccountListener);
        this.loginButton.setOnClickListener(loginListener);

        setImagePhoto();
    }

    private final View.OnClickListener createAccountListener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            final Intent createAccount = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(createAccount);
        }
    };

    private final View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            final String enteredUsername = emailAddress.getText().toString();
            final String enteredPassword = password.getText().toString();

            if(enteredUsername.equals(getPreference(Constants.USERNAME)) &&
                    enteredUsername.length() >= Constants.USERNAME_MINIMUM) {
                if(enteredPassword.equals(getPreference(Constants.PASSWORD)) &&
                        enteredPassword.length() >= Constants.PASSWORD_MINIMUM) {
                    makeToast("Logging in...");

                }
            }
        }
    };

    /** Checks to see if saved profile photo
     * If not, keeps default photo */
    private void setImagePhoto() {
        try {
            final FileInputStream fis = theC.openFileInput(Constants.PROFILE_PICTURE);
            final Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            this.pictureIV.setImageBitmap(b);
        }
        catch(Exception e) {
        }
    }

    private void makeToast(final String message) {
        Constants.makeToast(theC, message);
    }

    private String getPreference(final String tag) {
        return this.thePrefs.getString(tag, "");
    }

    private void setPreference(final String tag, final String value) {
        this.theEditor.putString(tag, value);
        this.theEditor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
