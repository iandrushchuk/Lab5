package com.example.alab5;

import android.net.Uri;

public class Item
{
    private String titile;
    private String info;
    private Uri ph;

    public Item(String titile, String info, Uri ph)
    {
        this.titile = titile;
        this.info = info;
        this.ph = ph;
    }

    public String getTitile()
    {
        return this.titile;
    }

    public String getInfo()
    {
        return this.info;
    }

    public  Uri getPh()
    {
        return this.ph;
    }
}
