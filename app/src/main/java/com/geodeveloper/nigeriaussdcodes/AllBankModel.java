package com.geodeveloper.nigeriaussdcodes;



public class AllBankModel {

    private int mImageResourceId;
    private String mTitle;
    private Class mClass;

    public AllBankModel(int mImageResourceId, String mTitle, Class mContext) {
        this.mImageResourceId = mImageResourceId;
        this.mTitle = mTitle;
        this.mClass = mContext;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public Class getmContext() {
        return mClass;
    }
}
