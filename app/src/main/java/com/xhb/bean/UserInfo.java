package com.xhb.bean;

import java.io.Serializable;

public class UserInfo implements Serializable{

    private String mUserName;
    private int mAge;
    private String mAvatarUrl;
    private float mWeight;


    public UserInfo(String userName, int age) {
        mUserName = userName;
        mAge = age;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public float getWeight() {
        return mWeight;
    }

    public void setWeight(float weight) {
        mWeight = weight;
    }
}
