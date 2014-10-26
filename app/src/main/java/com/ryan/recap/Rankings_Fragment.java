package com.ryan.recap;

import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.TextView;
import android.os.Bundle;

public class Rankings_Fragment extends Fragment {
    private static Context theC;

    private static void setContext(final Context theContext) {
        theC = theContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        final View theView = inflater.inflate(com.ryan.recap.R.layout.fragment_home, container, false);
        final TextView theV = (TextView) theView.findViewById(R.id.textview);
        theV.setText("Schools Fragment");

        return theView;
    }

}
