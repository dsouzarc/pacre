package com.ryan.recap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.ryan.recap.R;
import android.widget.EditText;
import android.net.Uri;
import android.content.Intent;
import android.provider.MediaStore.Images.Media;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.BitmapFactory;
import java.io.FileInputStream;
import android.provider.MediaStore;
import android.graphics.Matrix;
import java.io.FileOutputStream;
import android.view.View;
import android.widget.ImageView;
import android.database.Cursor;
import android.graphics.Bitmap;

public class CreateAccountActivity extends Activity {

    private static final int CHOOSE_PROFILE_PICTURE_RESULT = 1001;

    private Context theC;
    private SharedPreferences thePrefs;
    private SharedPreferences.Editor theEditor;

    private ImageView profilePicture;
    private EditText nameET, usernameET, passwordET, confirmPasswordET;
    private Button createAccountButton;

    private Bitmap currentImage = null;
    private Bitmap rotateImage = null;

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
        this.profilePicture = (ImageView) findViewById(R.id.profilePictureIV);

        this.createAccountButton.setOnClickListener(createAccountListener);
        this.profilePicture.setOnClickListener(chooseProfilePictureListener);

    }

    private final View.OnClickListener chooseProfilePictureListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(currentImage != null) {
                currentImage.recycle();
            }

            final Intent choosePhoto = new Intent();
            choosePhoto.setType("image/*");
            choosePhoto.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(choosePhoto,
                    "Choose Profile Photo"), CHOOSE_PROFILE_PICTURE_RESULT);
        }
    };

    private final View.OnClickListener createAccountListener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            //Check if name is valid
            final String name = nameET.getText().toString();

            if(currentImage == null) {
                makeToast("You must choose a profile picture");
                return;
            }

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

            if(password.length() < Constants.PASSWORD_MINIMUM) {
                makeToast("Please enter a longer password");
                return;
            }

            final String email = usernameET.getText().toString();
            if(!email.substring(email.length() - 4).equals(".edu")) {
                makeToast("Please enter a valid .edu email address");
            }
            if(!email.substring(email.length() - 13).equals("quinnipac.edu")) {
                makeToast("Please enter a valid quinnipac.edu");
            }
            if(email.length() < Constants.USERNAME_MINIMUM) {
                makeToast("Please enter a valid email address");
            }

            makeToast("Please check your email to confirm this account");

            theEditor.putString(Constants.NAME, name);
            theEditor.putString(Constants.USERNAME, email);
            theEditor.putString(Constants.PASSWORD, password);
            theEditor.commit();

            startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));
        }
    };

    private void saveProfilePicture() {
        try {
            final FileOutputStream output = theC.openFileOutput(Constants.PROFILE_PICTURE, Context.MODE_PRIVATE);
            currentImage.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.close();
        }
        catch (Exception e) {
            makeToast("Sorry, something went wrong while saving your profile picture");
        }
    }

    private Bitmap getImageBitmap() {
        try {
            final FileInputStream fis = theC.openFileInput(Constants.PROFILE_PICTURE);
            final Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            return b;
        }
        catch(Exception e) {
            makeToast("Sorry, something went wrong while fetching saved profile photo");
        }
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CHOOSE_PROFILE_PICTURE_RESULT && resultCode != 0) {
            final Uri imageUri = data.getData();

            try {
                this.currentImage = Media.getBitmap(this.getContentResolver(), imageUri);

                if(getOrientation(imageUri) != 0) {
                    final Matrix matrix = new Matrix();
                    matrix.postRotate(getOrientation(imageUri));

                    if(rotateImage != null) {
                        rotateImage.recycle();
                    }
                    rotateImage = Bitmap.createBitmap(currentImage, 0, 0, currentImage.getWidth(),
                            currentImage.getHeight(), matrix, true);
                    profilePicture.setImageBitmap(rotateImage);
                    currentImage.recycle();
                    currentImage = rotateImage;
                }
                else {
                    profilePicture.setImageBitmap(currentImage);
                }
                saveProfilePicture();
            }
            catch (Exception e) {
                makeToast("Sorry, something went wrong");
            }
        }
    }

    public int getOrientation(Uri photoUri) {
        final Cursor cursor = theC.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION },null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

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
