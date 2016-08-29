package com.xhb.bean;

/**
 * Created by onlystar on 2016/8/22.
 */
public class ActivityListInfo {
    private String mName;
    private Class mActiviy;

    public ActivityListInfo(String name, Class activiy) {
        this.mName = name;
        this.mActiviy = activiy;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Class getActiviy() {
        return mActiviy;
    }

    public void setActiviy(Class activiy) {
        mActiviy = activiy;
    }

    @Override
    public String toString() {
        return "ActivityListInfo{" +
                "mName='" + mName + '\'' +
                ", mActiviy=" + mActiviy +
                '}';
    }
}
