package com.xhb.bean;

public class WebURL {

    private int mID;
    private String mUrl;
    private String mContent;

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public String toString() {
        return "WebURL{" +
                "mID=" + mID +
                ", mUrl='" + mUrl + '\'' +
                ", mContent='" + mContent + '\'' +
                '}';
    }
}
