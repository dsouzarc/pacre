package com.ryan.recap.fragments;

import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.TextView;
import android.os.Bundle;

public class Fragment_Profile extends Fragment {
    private static Context theC;

    private static void setContext(final Context theContext) {
        theC = theContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        final View theView = inflater.inflate(com.ryan.recap.R.layout.fragment_profile, container, false);
        final TextView theV = (TextView) theView.findViewById(com.ryan.recap.R.id.textview);
        theV.setText("Home Fragment");

        return theView;
    }

}
