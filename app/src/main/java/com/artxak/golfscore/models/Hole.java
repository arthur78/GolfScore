package com.artxak.golfscore.models;

public class Hole {

    private String mName;
    private int mStroke = 0;

    public Hole(int position, int stroke) {
        mName = "Hole " + String.valueOf(position + 1);
        mStroke = stroke;
    }

    public void addStroke() {
        mStroke++;
    }

    public void removeStroke() {
        if (mStroke > 0) {
            mStroke--;
        }
    }

    public int getStroke() {
        return mStroke;
    }

    public void setStroke(int stroke) {
        mStroke = stroke;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return String.valueOf(mStroke);
    }
}
