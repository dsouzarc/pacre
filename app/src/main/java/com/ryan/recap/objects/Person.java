package com.ryan.recap.objects;

import android.graphics.drawable.Drawable;

public class Person {
    private final String name, username;
    private final Drawable profilePhoto;

    public Person(String name, String username, Drawable profilePhoto) {
        this.name = name;
        this.username = username;
        this.profilePhoto = profilePhoto;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Drawable getProfilePhoto() {
        return profilePhoto;
    }
}