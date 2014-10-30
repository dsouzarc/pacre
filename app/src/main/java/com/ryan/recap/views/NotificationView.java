package com.ryan.recap.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.ryan.recap.R;
import com.ryan.recap.objects.Notification;

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

        if(notification.getOtherDrawable() == null) {
            inflate(getContext(), R.layout.notifications_layout, this);
        }
        else {
            inflate(getContext(), R.layout.notifications_layout_two_images, this);
            ((ImageView) findViewById(R.id.pictureTwo)).setBackground(notification.getOtherDrawable());
        }
        ((TextView) findViewById(R.id.descriptionTV)).setText(notification.getMessage());
        ((TextView) findViewById(R.id.timeTV)).setText(notification.getTime());

        CircularImageView circularImageView = (CircularImageView)findViewById(R.id.profilePicture);
        circularImageView.setImageBitmap(circularImageView.drawableToBitmap(notification.getOtherPerson().getProfilePhoto()));
        circularImageView.setBorderWidth(10);
        circularImageView.setSelectorStrokeWidth(10);
        circularImageView.addShadow();
        circularImageView.invalidate();
    }
}
