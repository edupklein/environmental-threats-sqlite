package com.example.ameacas_ambientais;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditThreat extends AppCompatActivity {
    ThreatSQLiteDatabase db;
    EditText editTextAddress, editTextDate, editTextDescription;
    Threat current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_threat);

        editTextAddress = findViewById(R.id.editTextAddress);
        editTextDate = findViewById(R.id.editTextDate);
        editTextDescription = findViewById(R.id.editTextDescription);

        db = new ThreatSQLiteDatabase(getBaseContext());
        Long id = getIntent().getLongExtra("ID", 0);
        current = db.getThreat(id);

        editTextAddress.setText(current.getAddress());
        editTextDate.setText(current.getDate());
        editTextDescription.setText(current.getDescription());
    }

    public void updateThreat(View v) {
        current.setAddress(editTextAddress.getText().toString());
        current.setDate(editTextDate.getText().toString());
        current.setDescription(editTextDescription.getText().toString());

        db.updateThreat(current);
        finish();
    }

}
