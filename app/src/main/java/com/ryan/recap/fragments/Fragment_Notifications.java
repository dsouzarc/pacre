package com.ryan.recap.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ryan.recap.R;
import com.ryan.recap.objects.Notification;
import com.ryan.recap.objects.Person;
import com.ryan.recap.views.NotificationView;
public class Fragment_Notifications extends Fragment {
    private static Context theC;

    private static void setContext(final Context theContext) {
        theC = theContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        this.theC = getActivity().getApplicationContext();
        final View theView = inflater.inflate(R.layout.fragment_notifications, container, false);

        final LinearLayout theLayout = (LinearLayout) theView.findViewById(R.id.theLayout);

        final Person me = new Person("Ryan D'souza", "dsouzarc@gmail.com",
                getResources().getDrawable(R.drawable.blankprofile));

        for(int i = 0; i < 50; i++) {
            final Notification notif = new Notification(me, me.getName() + " liked your recap " + i,
                    String.valueOf(i), null);
            theLayout.addView(new NotificationView(theC, notif));
        }

        return theView;
    }

}
