package com.example.ameacas_ambientais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddThreat extends AppCompatActivity {
    ThreatSQLiteDatabase db;
    EditText editTextAddress, editTextDate, editTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("EVT", "Chegou em onCreat do AddThreat");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_threat);

        editTextAddress = findViewById(R.id.editTextAddress);
        editTextDate = findViewById(R.id.editTextDate);
        editTextDescription = findViewById(R.id.editTextDescription);

        db = new ThreatSQLiteDatabase(getBaseContext());
    }

    public void addThreat(View v){
        Threat t = new Threat();
        t.setAddress(editTextAddress.getText().toString());
        t.setDate(editTextDate.getText().toString());
        t.setDescription(editTextDescription.getText().toString());

        db.addThreat(t);
        finish();
    }

}
