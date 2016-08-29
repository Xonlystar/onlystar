package com.xhb.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Function:
 * Create date on 15/12/16.
 *
 * @author Conquer
 * @version 1.0
 */
public class UserData {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("content")
    private String mContent;

    @SerializedName("user")
    private User mUser;

    @SerializedName("images")
    private List<String> mImages;

    @SerializedName("block")
    private String mBlock;

    public String getBlock() {
        return mBlock;
    }

    public void setBlock(String block) {
        mBlock = block;
    }

    public String getDiscussNumber() {
        return mDiscussNumber;
    }

    public void setDiscussNumber(String discussNumber) {
        mDiscussNumber = discussNumber;
    }

    public String getDatetime() {
        return mDatetime;
    }

    public void setDatetime(String datetime) {
        mDatetime = datetime;
    }

    @SerializedName("discussNumber")
    private String mDiscussNumber;

    @SerializedName("datetime")
    private String mDatetime;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void setImages(List<String> images) {
        mImages = images;
    }

    public class User {
        @SerializedName("id")
        private long mID;

        @SerializedName("name")
        private String mName;

        @SerializedName("avatar")
        private String mAvatar;

        public long getID() {
            return mID;
        }

        public void setID(long ID) {
            mID = ID;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getAvatar() {
            return mAvatar;
        }

        public void setAvatar(String avatar) {
            mAvatar = avatar;
        }

        @Override
        public String toString() {
            return "User{" +
                    "mID=" + mID +
                    ", mName='" + mName + '\'' +
                    ", mAvatar='" + mAvatar + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "UserData{" +
                "mTitle='" + mTitle + '\'' +
                ", mContent='" + mContent + '\'' +
                ", mUser=" + mUser +
                ", mImages=" + mImages +
                ", mBlock='" + mBlock + '\'' +
                ", mDiscussNumber='" + mDiscussNumber + '\'' +
                ", mDatetime='" + mDatetime + '\'' +
                '}';
    }
}
