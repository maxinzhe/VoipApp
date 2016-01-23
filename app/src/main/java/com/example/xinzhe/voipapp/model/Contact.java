package com.example.xinzhe.voipapp.model;

/**
 * Created by Xinzhe on 2016/1/19.
 */
public class Contact {
    private String name;
    private String contact_id;
    private boolean online;

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
