package com.artxak.golfscore.models;


import android.text.TextUtils;


public class ScoreCard {

    private Hole[] mHoles;

    public ScoreCard(Hole[] holes) {
        mHoles = holes;
    }

    public ScoreCard(String scoreCardAsString) {
        String[] holesScores = scoreCardAsString.split(",");
        int holesCount = holesScores.length;
        mHoles = new Hole[holesCount];
        for (int i = 0; i < holesCount; i++) {
            mHoles[i] = new Hole(i, Integer.valueOf(holesScores[i]));
        }
    }

    public Hole getHole(int position) {
        return mHoles[position];
    }

    @Override
    public String toString() {
        int totalHoles = mHoles.length;
        String[] holesScores = new String[totalHoles];

        for (int i = 0; i < totalHoles; i++) {
            Hole hole = mHoles[i];
            holesScores[i] = hole.toString();
        }
        return TextUtils.join(",", holesScores);
    }

    public void reset() {
        for (int i = 0; i < mHoles.length; i++) {
            Hole hole = mHoles[i];
            hole.setStroke(0);
        }
    }

    public Hole[] getHoles() {
        return mHoles;
    }
}
