package com.ryan.recap;

import android.widget.Toast;
import android.util.Log;
import android.content.Context;

public final class Constants {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String PROFILE_PICTURE = "profile_picture";
    public static final String APP_TAG = "com.ryan.recap";

    public static final int USERNAME_MINIMUM = 14;
    public static final int PASSWORD_MINIMUM = 5;
    public static final int IMAGE_SIZE = 500;

    public static void log(final String message) {
        Log.e(APP_TAG, message);
    }

    public static void makeToast(final Context context, final String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
