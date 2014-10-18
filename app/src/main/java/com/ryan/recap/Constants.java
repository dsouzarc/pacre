package com.ryan.recap;

import android.widget.Toast;
import android.util.Log;
import android.content.Context;

public final class Constants {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static void log(final String message) {
        Log.e("com.ryan.recap", message);
    }

    public static void makeToast(final Context context, final String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
