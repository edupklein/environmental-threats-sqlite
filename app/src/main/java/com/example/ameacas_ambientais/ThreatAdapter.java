package com.example.ameacas_ambientais;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ThreatAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ThreatSQLiteDatabase db;

    public ThreatAdapter(Context ctx, ThreatSQLiteDatabase db) {
        inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.getThreats().size();
    }

    @Override
    public Object getItem(int position) {
        return db.getThreats().get(position);
    }

    @Override
    public long getItemId(int position) {
        return db.getThreats().get(position).getId();
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        v = inflater.inflate(R.layout.threat_list_item, null);
        TextView textViewDescription = v.findViewById(R.id.textViewDescription);
        textViewDescription.setText(db.getThreats().get(position).getDescription());
        TextView textViewDate = v.findViewById(R.id.textViewDate);
        textViewDate.setText(db.getThreats().get(position).getDate());
        return v;
    }
}
