package com.example.ameacas_ambientais;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView listThreats;
    ThreatAdapter threatAdapter;
    ThreatSQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new ThreatSQLiteDatabase(getBaseContext());
        listThreats = findViewById(R.id.listThreat);
        threatAdapter = new ThreatAdapter(getBaseContext(), db);
        listThreats.setAdapter(threatAdapter);

        listThreats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chageToUpdate(id);
            }
        });

        listThreats.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.removeThreat((Threat) threatAdapter.getItem(position));
                threatAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void changeToAdd(View v){
        Intent it = new Intent(getBaseContext(), AddThreat.class);
        startActivity(it);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                threatAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    public void chageToUpdate(Long id) {
        Intent it = new Intent(getBaseContext(), EditThreat.class);
        it.putExtra("ID", id);
        startActivity(it);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                threatAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }
}
