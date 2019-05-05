package com.cheguza.facilitycenter.Adapters;

public class VideoModel {

    String mThumb;
    String mPath;
    String mVideoText;
    String mSize;
    boolean mSelected;

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    public String getmVideoText() {
        return mVideoText;
    }

    public void setmVideoText(String mVideoText) {
        this.mVideoText = mVideoText;
    }

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public String getmThumb() {
        return mThumb;
    }

    public void setmThumb(String mThumb) {
        this.mThumb = mThumb;
    }

    public boolean ismSelected() {
        return mSelected;
    }

    public void setmSelected(boolean mSelected) {
        this.mSelected = mSelected;
    }
}
