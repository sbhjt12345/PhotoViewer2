package com.example.android.photoviewer2;

/**
 * Created by Jiantao on 4/10/17.
 */

public class createList {
    String imgTitle;
    Integer imgID;

    public createList(String imgTitle, Integer imgID){
        this.imgTitle = imgTitle;
        this.imgID = imgID;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String newTitle){
        this.imgTitle = newTitle;
    }

    public Integer getImgID(){
        return imgID;
    }

    public void setImgID(Integer newID){
        this.imgID = newID;
    }

}
