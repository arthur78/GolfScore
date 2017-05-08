package com.artxak.golfscore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.artxak.golfscore.R;
import com.artxak.golfscore.models.Hole;

public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.HoleViewHolder> {

    private Context mContext;
    private Hole[] mHoles;

    public HoleAdapter(Context context, Hole[] holes) {
        mContext = context;
        mHoles = holes;
    }

    @Override
    public HoleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scorecard_list_item, parent, false);
        return new HoleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HoleViewHolder holder, int position) {
        holder.bindHole(position);

    }

    @Override
    public int getItemCount() {
        return mHoles.length;
    }

    public class HoleViewHolder extends RecyclerView.ViewHolder {

        TextView mHoleNameLabel;
        TextView mHoleScoreLabel;
        Button mIncrementButton;
        Button mDecrementButton;

        public HoleViewHolder(View itemView) {
            super(itemView);
            mHoleNameLabel = (TextView) itemView.findViewById(R.id.holeNameLabel);
            mHoleScoreLabel = (TextView) itemView.findViewById(R.id.holeScoreLabel);
            mDecrementButton = (Button) itemView.findViewById(R.id.decrementButton);
            mIncrementButton = (Button) itemView.findViewById(R.id.incrementButton);
        }

        public void bindHole(int position) {
            final Hole hole = mHoles[position];
            mHoleNameLabel.setText(hole.getName() + ":");
            mHoleScoreLabel.setText(hole.getStroke() + "");

            mDecrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hole.removeStroke();
                    mHoleScoreLabel.setText(hole.getStroke() + "");
                }
            });

            mIncrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   hole.addStroke();
                    mHoleScoreLabel.setText(hole.getStroke() + "");
                }
            });
        }
    }

}
