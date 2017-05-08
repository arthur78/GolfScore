package com.artxak.golfscore.ui;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.artxak.golfscore.R;
import com.artxak.golfscore.adapters.HoleAdapter;
import com.artxak.golfscore.models.Hole;
import com.artxak.golfscore.models.ScoreCard;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "com.artxak.golfscore.preferences";
    private static final String SCORECARD_KEY = "scorecard_key";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private ScoreCard mScoreCard;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        initScoreCard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.reset) {
            clearAllStrokes();
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearAllStrokes() {
        mScoreCard.reset();
//        saveStrokesInPreferences();
        setScoreCardAdapter();
    }

    private void initScoreCard() {
        String savedScoreCard = mSharedPreferences.getString(SCORECARD_KEY, "");
        if (savedScoreCard.equals("")) {
            Hole[] holes = new Hole[18];
            for (int i = 0; i < holes.length; i++) {
                holes[i] = new Hole(i, 0);
            }
            mScoreCard = new ScoreCard(holes);
        } else {
            mScoreCard = new ScoreCard(savedScoreCard);
        }
        setScoreCardAdapter();
    }

    private void setScoreCardAdapter() {
        HoleAdapter adapter = new HoleAdapter(this, mScoreCard.getHoles());
        mRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveStrokesInPreferences();
    }

    private void saveStrokesInPreferences() {
        mEditor.putString(SCORECARD_KEY, mScoreCard.toString());
//        mEditor.putString(SCORECARD_KEY, "");
        mEditor.apply();
    }
}
