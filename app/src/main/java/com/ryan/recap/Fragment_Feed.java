package com.ryan.recap;

import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.TextView;
import android.os.Bundle;
public class Fragment_Feed extends Fragment {
    private static Context theC;

    private static void setContext(final Context theContext) {
        theC = theContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        final View theView = inflater.inflate(R.layout.fragment_feed, container, false);
        final TextView theV = (TextView) theView.findViewById(R.id.textview);
        theV.setText("Home Fragment");

        return theView;
    }

}
