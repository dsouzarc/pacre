package com.ryan.recap.objects;

import android.graphics.drawable.Drawable;

public class Notification {
    private final Person otherPerson;
    private final String message, time;
    private Drawable otherDrawable;

    public Notification(Person otherPerson, String message, String time, Drawable otherDrawable) {
        this.otherPerson = otherPerson;
        this.message = message;
        this.time = time;
        this.otherDrawable = otherDrawable;
    }

    public Person getOtherPerson() {
        return otherPerson;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public Drawable getOtherDrawable() {
        return otherDrawable;
    }
}
