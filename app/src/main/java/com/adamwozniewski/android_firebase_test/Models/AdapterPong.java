package com.adamwozniewski.android_firebase_test.Models;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adamwozniewski.android_firebase_test.R;

import java.util.ArrayList;

public class AdapterPong extends RecyclerView.Adapter<AdapterPong.PongHolder> {
    private ArrayList<Pong> pongs = new ArrayList<>();

    public ArrayList<Pong> getPongs() {
        return pongs;
    }

    @NonNull
    @Override
    public PongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pong, viewGroup, false);
        return new PongHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return this.pongs.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PongHolder pongHolder, int i) {
        Pong pong = this.pongs.get(i);
        pongHolder.textView.setText(pong.getSenderName());
    }

    public class PongHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public PongHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.textTitlePong);
        }

    }
}
