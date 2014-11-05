package com.ryan.recap.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import com.ryan.recap.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import java.io.FileInputStream;
import com.ryan.recap.Constants;
import android.os.AsyncTask;

public class Fragment_Profile extends Fragment {
    private static Context theC;

    private static void setContext(final Context theContext) {
        theC = theContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        final View theView = inflater.inflate(R.layout.fragment_profile, container, false);

        new ShowPicture((ImageView) theView.findViewById(R.id.profilePicture)).execute(Constants.PROFILE_PICTURE);


        return theView;
    }

    private class ShowPicture extends AsyncTask<String, Void, Bitmap> {
        private final ImageView view;

        public ShowPicture(final ImageView view) {
            this.view = view;
        }

        @Override
        public Bitmap doInBackground(final String... fileNames) {
            try {
                final FileInputStream fis = theC.openFileInput(Constants.PROFILE_PICTURE);
                return BitmapFactory.decodeStream(fis);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void onPostExecute(final Bitmap bm) {
            if(bm == null) {
                makeToast("Error retrieving profile photo");
                return;
            }

            view.setImageBitmap(bm);
        }
    }

    public void makeToast(final String m) {
        Toast.makeText(theC, m, Toast.LENGTH_LONG).show();
    }


}
