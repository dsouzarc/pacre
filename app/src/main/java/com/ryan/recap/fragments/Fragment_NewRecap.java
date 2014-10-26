package com.ryan.recap.fragments;

import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.os.Bundle;

public class Fragment_NewRecap extends Fragment {
    private static Context theC;

    private static void setContext(final Context theContext) {
        theC = theContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        final View theView = inflater.inflate(com.ryan.recap.R.layout.fragment_newrecap, container, false);
        return theView;
    }

}
