package com.example.dtlapp;

public class Upload {
    private String mCords;
    private String mRemarks;
    private String mTime;
    private  String mImageURL;

    //public Upload(String trim, String s)
    //{

    //}

    public Upload(String Cords, String Time, String Remarks, String ImageURL)
    {
        if(Cords.trim().equals(""))
        {
            Cords  = "NOT FOUND";
        }
        if(Time.trim().equals(""))
        {
            Time  = "NOT FOUND";
        }
        if(Remarks.trim().equals(""))
        {
            Remarks  = "NOT FOUND";
        }

        mCords = Cords;
        mImageURL = ImageURL;
        mTime = Time;
        mRemarks = Remarks;

    }

    public String getcords()
    {
        return mCords;
    }

    public String gettime()
    {
        return mTime;
    }

    public String getremarks()
    {
        return mRemarks;
    }

    public void setcords(String Cords)
    {
        mCords = Cords;
    }
    public void settime(String Time)
    {
        mTime = Time;
    }
    public void setremarks(String Remarks)
    {
        mRemarks = Remarks;
    }
    public String getImageURL()
    {
        return mImageURL;
    }
    public void setImageURL(String ImageURL)
    {
        mImageURL = ImageURL;
    }
}
