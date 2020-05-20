package com.example.dtlapp;

import com.google.firebase.database.Exclude;

public class Upload {
    private String mCords;
    private String mRemarks;
    private String mTime;
    private String mImageURL;
    private String mKey;
    private String mselectedRB;

    public Upload() {

    }

    public Upload(String Cords, String Time, String Remarks, String ImageURL, String selectedRB) {
        if (Cords.trim().equals("")) {
            Cords = "NOT FOUND";
        }
        if (Time.trim().equals("")) {
            Time = "NOT FOUND";
        }
        if (Remarks.trim().equals("")) {
            Remarks = "NOT FOUND";
        }

        mCords = Cords;
        mImageURL = ImageURL;
        mTime = Time;
        mRemarks = Remarks;
        mselectedRB = selectedRB;


    }

    public String getcords() {
        return mCords;
    }

    public String gettime() {
        return mTime;
    }

    public String getremarks() {
        return mRemarks;
    }

    public String getselectedRB()
    {
        return mselectedRB;
    }

    public void setcords(String Cords) {
        mCords = Cords;
    }

    public void settime(String Time) {
        mTime = Time;
    }

    public void setremarks(String Remarks) {
        mRemarks = Remarks;
    }

    public void setselectedRB(String selectedRB){
        mselectedRB = selectedRB;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String ImageURL) {
        mImageURL = ImageURL;
    }

    @Exclude
    public String getKey()
    {
        return mKey;
    }

    @Exclude
    public void setKey(String key)
    {
        mKey = key;
    }
}
