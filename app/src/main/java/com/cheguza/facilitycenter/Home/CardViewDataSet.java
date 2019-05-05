package com.cheguza.facilitycenter.Home;

public class CardViewDataSet {

    int image;
    String textname;
    int id;

    public CardViewDataSet(String textname, int image,int id) {
        this.textname = textname;
        this.image = image;
        this.id=id;
    }



    public String getTextname() {
        return textname;
    }

    public void setTextname(String textname) {
        this.textname = textname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public int getId() {
        return id;
    }



}
