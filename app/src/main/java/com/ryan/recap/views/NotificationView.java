package com.ryan.recap.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.ryan.recap.objects.Notification;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.ryan.recap.R;
import android.widget.RelativeLayout;

public class NotificationView extends RelativeLayout{
    private final Context theC;
    private final Notification notification;

    public NotificationView(Context context, Notification notification) {
        super(context);
        this.theC = context;
        this.notification = notification;
        setUp();
    }

    public NotificationView(Context context, AttributeSet attrs, Notification notification) {
        super(context, attrs);
        this.theC = context;
        this.notification = notification;
        setUp();
    }

    public NotificationView(Context context, AttributeSet attrs, int defStyle, Notification notif) {
        super(context, attrs, defStyle);
        this.theC = context;
        this.notification = notif;
        setUp();
    }

    private void setUp() {
        inflate(getContext(), R.layout.notifications_layout, this);
        ((ImageView)findViewById(R.id.profilePicture)).setBackground(notification.getOtherDrawable());
        ((TextView)findViewById(R.id.descriptionTV)).setText(notification.getMessage());




    }



}
